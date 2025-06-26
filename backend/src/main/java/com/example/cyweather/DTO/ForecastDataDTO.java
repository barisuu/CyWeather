package com.example.cyweather.DTO;

import com.example.cyweather.domain.City;
import lombok.Data;

@Data
public class ForecastDataDTO {
    private String date;
    private Day day;
}
