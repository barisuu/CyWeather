package com.example.cyweather.repository;

import com.example.cyweather.domain.City;
import com.example.cyweather.domain.CurrentData;
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
        CurrentData weather = new CurrentData(city,
                "Partly Cloudy",
                "a",
                LocalDateTime.now(),
                28.5);

        WeatherData returnedWeatherData = weatherDataRepository.save(weather);

        Optional<WeatherData> result = weatherDataRepository.findById(returnedWeatherData.getId());

        assertThat(result).isNotEmpty();
        assertThat(result.get().getCity()).isEqualTo("Nicosia");
        assertThat(result.get().getWeatherCondition()).isEqualTo("Partly Cloudy");

    }


}
