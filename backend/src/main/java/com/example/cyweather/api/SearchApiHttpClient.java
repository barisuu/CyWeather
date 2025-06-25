package com.example.cyweather.api;

import com.example.cyweather.DTO.CityDTO;
import com.example.cyweather.domain.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class SearchApiHttpClient implements SearchApiClient{
    private final RestTemplate restTemplate;

    @Value("${weatherapi.key}")
    private String apiKey;

    public SearchApiHttpClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public City fetchCity(Long cityId) {
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.weatherapi.com")
                .path("/v1/search.json")
                .queryParam("key",apiKey)
                .queryParam("q","id:"+cityId)
                .build()
                .toUriString();
        CityDTO[] cityDTOSearchResults = restTemplate.getForObject(url, CityDTO[].class);

        CityDTO cityDTOInfo = cityDTOSearchResults[0];


        City newCity = new City(cityId,
                cityDTOInfo.getName(),
                cityDTOInfo.getRegion(),
                cityDTOInfo.getCountry(),
                cityDTOInfo.getLat(),
                cityDTOInfo.getLon());

        return newCity;
    }

    @Override
    public List<CityDTO> searchCitiesByName(String cityName) {
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.weatherapi.com")
                .path("/v1/search.json")
                .queryParam("key",apiKey)
                .queryParam("q",cityName)
                .build()
                .toUriString();
        CityDTO[] cityDTOSearchResultsArray = restTemplate.getForObject(url, CityDTO[].class);
        if(cityDTOSearchResultsArray ==null){
                return Collections.emptyList();
        }
        List<CityDTO> searchResults = List.of(cityDTOSearchResultsArray);
        return searchResults;
    }
}
