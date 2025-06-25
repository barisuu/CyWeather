package com.example.cyweather.controller;

import com.example.cyweather.DTO.CityDTO;
import com.example.cyweather.api.SearchApiClient;
import com.example.cyweather.domain.City;
import com.example.cyweather.domain.WeatherData;
import com.example.cyweather.service.CityService;
import com.example.cyweather.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;
    private final CityService cityService;
    private final SearchApiClient searchApiClient;

    public WeatherController(WeatherService weatherService, CityService cityService, SearchApiClient searchApiClient) {
        this.weatherService = weatherService;
        this.cityService = cityService;
        this.searchApiClient = searchApiClient;
    }

    @GetMapping("/current")
    public ResponseEntity<WeatherData> getCurrentWeather(@RequestParam Long cityId){
        try{
            City city = cityService.createOrGetCity(cityId);
            WeatherData data = weatherService.getCurrentWeather(city);
            return ResponseEntity.ok(data);
        } catch (Exception e){
            //TODO Specific exception handling.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/history")
    public ResponseEntity<List<WeatherData>> getHistoricalWeather(@RequestParam Long cityId, @RequestParam Integer daysInPast){
        try{
            if(daysInPast >14){
                throw new Exception("Days in past can't be larger than 14");
            }
            City city = cityService.createOrGetCity(cityId);
            List<WeatherData> pastForecastData = weatherService.getHistoricalWeather(city,daysInPast);
            return ResponseEntity.ok(pastForecastData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/cities/search")
    public ResponseEntity<List<CityDTO>> searchCities(@RequestParam String name){
        List<CityDTO> results = cityService.searchCitiesByName(name);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<City> searchCity(@PathVariable Long id){
        City city = cityService.createOrGetCity(id);
        return ResponseEntity.ok(city);
    }

    @PostMapping("/init")
    public ResponseEntity<Void> initializeApp(){
        List<String> initialCityNames = List.of("Nicosia-Nicosia-Cyprus",
                "Girne-Kyrenia-Cyprus",
                "Magusa-Famagusta-Cyprus");

        for(String cityName:initialCityNames){
            List<CityDTO> searchResults = cityService.searchCitiesByName(cityName);
            if(!searchResults.isEmpty()){
                cityService.createOrGetCity(searchResults.get(0).getId());
            }
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<WeatherData>> getHomePageWeather(){
        return ResponseEntity.ok(new ArrayList<WeatherData>());
    }

}
