package com.example.cyweather.service;

import com.example.cyweather.api.WeatherApiClient;
import com.example.cyweather.domain.WeatherData;
import com.example.cyweather.repository.WeatherDataRepository;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private final WeatherDataRepository weatherDataRepository;
    private final WeatherApiClient weatherApiClient;

    public WeatherService(WeatherDataRepository weatherDataRepository, WeatherApiClient weatherApiClient) {
        this.weatherDataRepository = weatherDataRepository;
        this.weatherApiClient = weatherApiClient;
    }

    public WeatherData fetchAndSaveWeather(String city){
        WeatherData data = weatherApiClient.fetchCurrentWeather(city);
        return weatherDataRepository.save(data);
    }
}
