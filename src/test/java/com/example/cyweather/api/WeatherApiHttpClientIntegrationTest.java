package com.example.cyweather.api;

import com.example.cyweather.domain.WeatherData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class WeatherApiHttpClientIntegrationTest {

    @Autowired
    private WeatherApiClient client;

    @Test
    void fetchCurrentWeatherReturnsWeatherData(){
        WeatherData data = client.fetchCurrentWeather("Nicosia");

        assertNotNull(data);
        assertEquals("Nicosia", data.getCity());
        assertNotNull(data.getTemp());
        assertNotNull(data.getWeathercondition());
        assertNotNull(data.getTime());
        System.out.println(data);
    }
}
