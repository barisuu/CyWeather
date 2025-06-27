package com.example.cyweather.DTO;

import lombok.Data;

@Data
public class CityDTO {
    private Long id;
    private String name;
    private String region;
    private String country;
    private Double lat;
    private Double lon;
}
