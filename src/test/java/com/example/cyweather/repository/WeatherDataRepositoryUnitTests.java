package com.example.cyweather.repository;

import com.example.cyweather.domain.City;
import com.example.cyweather.domain.WeatherData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class WeatherDataRepositoryUnitTests {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Test
    void shouldSaveAndRetrieveWeatherData()
    {
        City city = new City(540341L,"Nicosia", "Nicosia", "Cyprus",35.17,33.37);
        WeatherData weather = new WeatherData(city,
                "Partly Cloudy",
                28.5,
                LocalDateTime.now());

        WeatherData returnedWeatherData = weatherDataRepository.save(weather);

        Optional<WeatherData> result = weatherDataRepository.findById(returnedWeatherData.getId());

        assertThat(result).isNotEmpty();
        assertThat(result.get().getCity()).isEqualTo("Nicosia");
        assertThat(result.get().getWeathercondition()).isEqualTo("Partly Cloudy");

    }


}
