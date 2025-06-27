package com.example.cyweather.DTO;


import lombok.Data;

@Data
public class Day {
    private Double maxtemp_c;
    private Double mintemp_c;
    private Double avgtemp_c;
    private Condition condition;

}
