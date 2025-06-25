package com.example.cyweather.repository;

import com.example.cyweather.domain.City;
import com.example.cyweather.domain.WeatherData;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
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

    City city = new City(540341L,"Nicosia", "Nicosia", "Cyprus",35.17,33.37);

   /*TODO @Test
    void findTopByCityOrderByTimeDescReturnsFreshData(){
        WeatherData oldData = new WeatherData(
                city,
                "Sunny",
                28.5,
                LocalDateTime.now().minusHours(1)
        );
        WeatherData newData = new WeatherData(
                city,
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

    }*/


}
