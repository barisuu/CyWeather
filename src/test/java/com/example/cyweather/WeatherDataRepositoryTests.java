package com.example.cyweather;

import com.example.cyweather.domain.WeatherData;
import com.example.cyweather.repository.WeatherDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class WeatherDataRepositoryTests {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Test
    void shouldSaveAndRetrieveWeatherData()
    {
        String city = "Nicosia";
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
