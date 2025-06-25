package com.example.cyweather.service;

import com.example.cyweather.api.WeatherApiClient;
import com.example.cyweather.domain.City;
import com.example.cyweather.domain.WeatherData;
import com.example.cyweather.repository.WeatherDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class WeatherServiceTests {
    private WeatherApiClient weatherApiClient;
    private WeatherDataRepository weatherDataRepository;
    private WeatherService weatherService;

    @BeforeEach
    void setUp(){
        weatherApiClient = mock(WeatherApiClient.class);
        weatherDataRepository = mock(WeatherDataRepository.class);
        weatherService = new WeatherService(weatherDataRepository,weatherApiClient);
    }

    @Test
    void shouldCallApiAndSave(){
        City city = new City(540341L,"Nicosia", "Nicosia", "Cyprus",35.17,33.37);
        WeatherData mockData = new WeatherData(
                city,
                "Partly Cloudy",
                28.5,
                LocalDateTime.now()
        );
        when(weatherApiClient.fetchCurrentWeather(city)).thenReturn(mockData);
        when(weatherDataRepository.save(mockData)).thenReturn(mockData);

        WeatherData result = weatherService.fetchAndSaveCurrentWeather(city);


        assertThat(result.getCity()).isEqualTo(city);
        verify(weatherApiClient).fetchCurrentWeather(city);
        verify(weatherDataRepository).save(mockData);
    }
}
