package com.example.cyweather.api;

import com.example.cyweather.domain.City;
import com.example.cyweather.domain.CurrentData;
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
        //TODO: Make sure test uses actual database not mock city.
        City city = new City(540341L,"Nicosia", "Nicosia", "Cyprus",35.17,33.37);
        CurrentData data = client.fetchCurrentWeather(city);

        assertNotNull(data);
        assertEquals("Nicosia", data.getCity());
        assertNotNull(data.getCurrentTemp());
        assertNotNull(data.getWeatherCondition());
        assertNotNull(data.getDateTime());
        System.out.println(data);
    }
}
