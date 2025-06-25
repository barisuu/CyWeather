package com.example.cyweather.repository;

import com.example.cyweather.domain.WeatherData;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
public class WeatherDataRepositoryIntegrationTests {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Test
    void findTopByCityOrderByTimeDescReturnsFreshData(){
        WeatherData oldData = new WeatherData(
                "Nicosia",
                "Sunny",
                28.5,
                LocalDateTime.now().minusHours(1)
        );
        WeatherData newData = new WeatherData(
                "Nicosia",
                "Partly cloudy",
                25.0,
                LocalDateTime.now()
        );

        weatherDataRepository.save(oldData);
        weatherDataRepository.save(newData);

        Optional<WeatherData> result = weatherDataRepository.findTopByCityOrderByTimeDesc("Nicosia");

        assertTrue(result.isPresent());
        assertEquals("Partly cloudy",result.get().getWeathercondition());
        assertEquals(25.0,result.get().getTemp());

    }


}
