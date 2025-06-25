package com.example.cyweather.repository;

import com.example.cyweather.domain.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
}
