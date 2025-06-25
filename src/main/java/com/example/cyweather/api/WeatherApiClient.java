package com.example.cyweather.api;

import com.example.cyweather.domain.WeatherData;


public interface WeatherApiClient {
    WeatherData fetchCurrentWeather(String city);
}

