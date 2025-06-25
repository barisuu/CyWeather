package com.example.cyweather.DTO;

import lombok.Data;

@Data
public class CityDTO {
    //This DTO also has the id and is essentially same as City class
    //However, this is solely used for external api response mapping, thus can be changed without affecting
    //the database.
    private Long id;
    private String name;
    private String region;
    private String country;
    private Double lat;
    private Double lon;
}
