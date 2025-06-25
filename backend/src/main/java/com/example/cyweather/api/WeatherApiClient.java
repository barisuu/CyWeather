package com.example.cyweather.api;

import com.example.cyweather.domain.City;
import com.example.cyweather.domain.WeatherData;

import java.time.LocalDate;
import java.util.List;


public interface WeatherApiClient {
    WeatherData fetchCurrentWeather(City city);
    List<WeatherData> fetchHistoricalWeather(City city, LocalDate dt);
}

