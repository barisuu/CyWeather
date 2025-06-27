package com.example.cyweather.DTO;

import lombok.Data;

@Data
public class CityCurrentWeatherDTO {
    CityDTO city;
    CurrentDataDTO current;
}
