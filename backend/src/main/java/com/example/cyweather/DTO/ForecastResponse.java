package com.example.cyweather.DTO;

import lombok.Data;

@Data
public class ForecastResponse {
    private Forecast forecast;
    private CityDTO cityDTO;
}

