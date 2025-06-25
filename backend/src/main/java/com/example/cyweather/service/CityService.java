package com.example.cyweather.service;

import com.example.cyweather.DTO.CityDTO;
import com.example.cyweather.api.SearchApiClient;
import com.example.cyweather.domain.City;
import com.example.cyweather.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final SearchApiClient searchApiClient;

    public CityService(CityRepository cityRepository, SearchApiClient searchApiClient) {
        this.cityRepository = cityRepository;
        this.searchApiClient = searchApiClient;
    }

    public City createOrGetCity(long id){
        return cityRepository.findById(id)
                .orElseGet(() -> {
                            City city = searchApiClient.fetchCity(id);
                            cityRepository.save(city);
                            return city;
                        }
                        );
    }

    public List<CityDTO> searchCitiesByName(String cityName){
        //Implemented this way for future scalability, currently just a passthrough
        return searchApiClient.searchCitiesByName(cityName);
    }
}
