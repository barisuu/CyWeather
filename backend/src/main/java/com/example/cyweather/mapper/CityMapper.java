package com.example.cyweather.mapper;

import com.example.cyweather.DTO.CityDTO;
import com.example.cyweather.domain.City;

public class CityMapper {
    public static CityDTO toEntity(City city){
        CityDTO cityDTO = new CityDTO();

        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        cityDTO.setRegion(city.getRegion());
        cityDTO.setCountry(city.getCountry());
        cityDTO.setLat(city.getLat());
        cityDTO.setLon(city.getLon());

        return cityDTO;
    }
}
