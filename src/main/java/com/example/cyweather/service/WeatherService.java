package com.example.cyweather.service;

import com.example.cyweather.api.WeatherApiClient;
import com.example.cyweather.domain.WeatherData;
import com.example.cyweather.repository.WeatherDataRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    //Used only for manual user refresh. For all other purposes use getCurrentWeather
    public WeatherData fetchAndSaveCurrentWeather(String city){
        WeatherData data = weatherApiClient.fetchCurrentWeather(city);
        return weatherDataRepository.save(data);
    }

    public WeatherData getCurrentWeather(String city){
        Optional<WeatherData> latest = weatherDataRepository.findTopByCityOrderByTimeDesc(city);

        if(latest.isPresent() && isDataFresh(latest.get())){
            return latest.get();
        }

        WeatherData freshData = weatherApiClient.fetchCurrentWeather(city);
        return weatherDataRepository.save(freshData);
    }

    public List<WeatherData> getHistoricalWeather(String city, Integer daysInPast){
        LocalDate dtDate = LocalDate.now().minusDays(daysInPast);
        List<WeatherData> historicalWeather = weatherApiClient.fetchHistoricalWeather(city,dtDate);
        return historicalWeather;
    }

    private boolean isDataFresh(WeatherData data){
        return Duration.between(data.getTime(), LocalDateTime.now()).toMinutes() < 15;
    }
}
