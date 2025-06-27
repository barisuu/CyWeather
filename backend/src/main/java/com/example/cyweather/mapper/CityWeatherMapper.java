package com.example.cyweather.mapper;

import com.example.cyweather.DTO.*;
import com.example.cyweather.domain.City;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CityWeatherMapper {
    public static CityWeatherDTO toEntity(City city, List<ForecastDataDTO> forecastData, CurrentDataDTO current){
        CityWeatherDTO cityWeatherDTO = new CityWeatherDTO();

        List<ForecastDataDTO> historical = new ArrayList<>();
        List<ForecastDataDTO> forecast = new ArrayList<>();
        for(ForecastDataDTO data:forecastData){
            if(LocalDateTime.parse(data.getDate()).toLocalDate().isBefore(LocalDate.now())){
                historical.add(data);
            }else{
                forecast.add(data);
            }
        }

        cityWeatherDTO.setCity(city);
        cityWeatherDTO.setCurrent(current);
        cityWeatherDTO.setForecast(forecast);
        cityWeatherDTO.setHistory(historical);

        return cityWeatherDTO;
    }

    public static CityCurrentWeatherDTO toCurrentEntity(CityDTO city, CurrentDataDTO current){
        CityCurrentWeatherDTO currentWeatherDTO = new CityCurrentWeatherDTO();

        currentWeatherDTO.setCity(city);
        currentWeatherDTO.setCurrent(current);

        return currentWeatherDTO;
    }
}
