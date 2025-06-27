package com.example.cyweather.api;

import com.example.cyweather.domain.City;
import com.example.cyweather.domain.CurrentData;
import com.example.cyweather.domain.ForecastData;

import java.time.LocalDate;
import java.util.List;


public interface WeatherApiClient {
    CurrentData fetchCurrentWeather(City city);
    List<ForecastData> fetchHistoricalWeather(City city, LocalDate dt);
    List<ForecastData> fetchForecastWeather(City city, Integer dt);
}

