package com.example.cyweather.DTO;

import com.example.cyweather.domain.City;
import lombok.Data;

@Data
public class CityCurrentWeatherDTO {
    CityDTO city;
    CurrentDataDTO current;
}
