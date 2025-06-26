package com.example.cyweather.DTO;

import com.example.cyweather.domain.City;
import lombok.Data;

@Data
public class CurrentDataDTO {
    private Double temp_c;
    private Condition condition;
}
