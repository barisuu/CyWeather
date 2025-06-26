package com.example.cyweather.DTO;

import com.example.cyweather.domain.City;
import lombok.Data;

import java.util.List;

@Data
public class CityWeatherDTO {
    City city;
    CurrentDataDTO current;
    List<ForecastDataDTO> forecast;
    List<ForecastDataDTO> history;
}
