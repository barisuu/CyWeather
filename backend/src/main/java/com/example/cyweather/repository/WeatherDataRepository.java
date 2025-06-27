package com.example.cyweather.repository;

import com.example.cyweather.domain.City;
import com.example.cyweather.domain.CurrentData;
import com.example.cyweather.domain.ForecastData;
import com.example.cyweather.domain.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {

    Optional<CurrentData> findTopByCityOrderByDateTimeDesc(City city);

    ArrayList<ForecastData> findByCityAndDateTimeBetween(City city, LocalDateTime dateTimeAfter, LocalDateTime dateTimeBefore);

}

