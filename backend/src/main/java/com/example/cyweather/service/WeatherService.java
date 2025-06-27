package com.example.cyweather.service;

import com.example.cyweather.DTO.*;
import com.example.cyweather.api.WeatherApiClient;
import com.example.cyweather.domain.City;
import com.example.cyweather.domain.CurrentData;
import com.example.cyweather.domain.ForecastData;
import com.example.cyweather.domain.WeatherData;
import com.example.cyweather.mapper.CityMapper;
import com.example.cyweather.mapper.CityWeatherMapper;
import com.example.cyweather.mapper.CurrentMapper;
import com.example.cyweather.mapper.ForecastMapper;
import com.example.cyweather.repository.WeatherDataRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {
    private final WeatherDataRepository weatherDataRepository;
    private final WeatherApiClient weatherApiClient;

    public WeatherService(WeatherDataRepository weatherDataRepository, WeatherApiClient weatherApiClient) {
        this.weatherDataRepository = weatherDataRepository;
        this.weatherApiClient = weatherApiClient;
    }

    public CurrentDataDTO getCurrentWeather(City city){
        Optional<CurrentData> latest = weatherDataRepository.findTopByCityOrderByDateTimeDesc(city);

        if(latest.isPresent() && isDataFresh(latest.get())){
            return CurrentMapper.toEntity(latest.get());
        }

        CurrentData freshData = weatherApiClient.fetchCurrentWeather(city);
        weatherDataRepository.save(freshData);
        return CurrentMapper.toEntity(freshData);
    }

    // Currently, if any part of the existing data for Forecast fetching is missing, it fetches
    // the entire range once again. However, this can be improved by fetching only the missing part.
    // If API call amount or time to fetch isn't a concern, this can be achieved by fetching individual
    // missing days. However, a better version would probably be fetching the minimum range required
    // that would restore missing data. Currently, only missing days are saved anyways so that logic doesn't need
    // any change.

    //TODO: Cleanup
    /* Method used by debugging endpoint.
    public List<ForecastDataDTO> getHistoricalWeather(City city, Integer daysInPast){
        LocalDate dtDate = LocalDate.now().minusDays(daysInPast);

        List<ForecastData> existingData = weatherDataRepository.findByCityAndDateTimeBetween(
                city,
                dtDate.atStartOfDay(),
                LocalDateTime.now().minusDays(1)
        );

        List<ForecastDataDTO> dtoList = new ArrayList<>();
        if(existingData.size() < daysInPast){
            List<ForecastData> fetchedHistoricalWeather = weatherApiClient.fetchHistoricalWeather(city,dtDate);
            for(ForecastData data:fetchedHistoricalWeather){
                weatherDataRepository.save(data);
                dtoList.add(ForecastMapper.toEntity(data));
            }
        }else{
            for(ForecastData data:existingData){
                dtoList.add(ForecastMapper.toEntity(data));
            }
        }
        return dtoList;
    }*/

    public CityWeatherDTO getCityWeatherDetails(City city, Integer daysInPast, Integer daysInFuture){
        LocalDate dtDate = LocalDate.now().minusDays(daysInPast);

        List<ForecastData> existingData = weatherDataRepository.findByCityAndDateTimeBetween(
                city,
                dtDate.atStartOfDay(),
                LocalDate.now().plusDays(daysInFuture).atStartOfDay()
        );

        List<ForecastDataDTO> dtoList = new ArrayList<>();
        if(existingData.size() < daysInFuture + daysInPast){
            List<ForecastData> fetchedWeather = weatherApiClient.fetchHistoricalWeather(city,dtDate);
            fetchedWeather.addAll(weatherApiClient.fetchForecastWeather(city,daysInFuture));
            for(ForecastData data:fetchedWeather){
                if(!existingData.contains(data)){
                    weatherDataRepository.save(data);
                }
                dtoList.add(ForecastMapper.toEntity(data));
            }
        }else{
            for(ForecastData data:existingData){
                dtoList.add(ForecastMapper.toEntity(data));
            }
        }

        CurrentDataDTO current = getCurrentWeather(city);

        return CityWeatherMapper.toEntity(city,dtoList,current);
    }

    public CityCurrentWeatherDTO getCurrentCityWeather(City city){
        CurrentDataDTO currentData = getCurrentWeather(city);
        CityDTO cityDTO = CityMapper.toEntity(city);

        return CityWeatherMapper.toCurrentEntity(cityDTO,currentData);
    }

    private boolean isDataFresh(WeatherData data){
        return Duration.between(data.getDateTime(), LocalDateTime.now()).toMinutes() < 15;
    }
}
