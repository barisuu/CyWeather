package com.example.cyweather.DTO;

import lombok.Data;

import java.util.List;


@Data
public class Forecast {
    private List<ForecastDay> forecastday;
}
