package com.example.cyweather.api;

import com.example.cyweather.domain.WeatherData;

import java.time.LocalDate;
import java.util.List;


public interface WeatherApiClient {
    WeatherData fetchCurrentWeather(String city);
    List<WeatherData> fetchHistoricalWeather(String city, LocalDate dt);
}

