package com.example.cyweather.api;

import com.example.cyweather.DTO.CityDTO;
import com.example.cyweather.domain.City;

import java.util.List;

public interface SearchApiClient {
    City fetchCity(Long cityId);
    List<CityDTO> searchCitiesByName(String cityName);
}
