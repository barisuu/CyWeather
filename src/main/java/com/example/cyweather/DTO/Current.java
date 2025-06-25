package com.example.cyweather.DTO;

import lombok.Data;

@Data
public class Current {
    private Double temp_c;
    private Condition condition;
}
