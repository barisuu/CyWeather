package com.example.cyweather.repository;

import com.example.cyweather.domain.City;
import com.example.cyweather.domain.CurrentData;
import com.example.cyweather.domain.WeatherData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@TestPropertySource(locations = "classpath:application.properties")
public class WeatherDataRepositoryUnitTests {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Autowired
    private CityRepository cityRepository;

    @Test
    void shouldSaveAndRetrieveWeatherData()
    {
        City city = new City(540341L,"Nicosia", "Nicosia", "Cyprus",35.17,33.37);
        cityRepository.save(city);

        CurrentData weather = new CurrentData(
                city,
                "Partly Cloudy",
                "a",
                LocalDateTime.now(),
                28.5);

        CurrentData returnedWeatherData = weatherDataRepository.save(weather);

        Optional<WeatherData> result = weatherDataRepository.findById(returnedWeatherData.getId());

        assertThat(result).isNotEmpty();
        assertThat(result.get().getCity().getName()).isEqualTo("Nicosia");
        assertThat(result.get().getWeatherCondition()).isEqualTo("Partly Cloudy");

    }


}
