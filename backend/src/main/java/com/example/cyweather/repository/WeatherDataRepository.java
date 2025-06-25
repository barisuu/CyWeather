package com.example.cyweather.repository;

import com.example.cyweather.domain.City;
import com.example.cyweather.domain.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {

    Optional<WeatherData> findTopByCityOrderByTimeDesc(City city);
}
