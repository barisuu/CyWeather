package com.example.cyweather.controller;

import com.example.cyweather.DTO.*;
import com.example.cyweather.api.SearchApiClient;
import com.example.cyweather.domain.City;
import com.example.cyweather.domain.CurrentData;
import com.example.cyweather.domain.ForecastData;
import com.example.cyweather.domain.WeatherData;
import com.example.cyweather.mapper.CityMapper;
import com.example.cyweather.service.CityService;
import com.example.cyweather.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<CurrentDataDTO> getCurrentWeather(@RequestParam Long cityId){
        try{
            City city = cityService.createOrGetCity(cityId);
            CurrentDataDTO data = weatherService.getCurrentWeather(city);
            return ResponseEntity.ok(data);
        } catch (Exception e){
            //TODO Specific exception handling.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/history")
    public ResponseEntity<List<ForecastDataDTO>> getHistoricalWeather(@RequestParam Long cityId, @RequestParam Integer daysInPast){
        try{
            if(daysInPast >14){
                throw new Exception("Days in past can't be larger than 14");
            }
            City city = cityService.createOrGetCity(cityId);
            List<ForecastDataDTO> pastForecastData = weatherService.getHistoricalWeather(city,daysInPast);
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
    public ResponseEntity<CityWeatherDTO> getCityDetails(@PathVariable Long id, @RequestParam Integer daysInPast, @RequestParam Integer daysInFuture){
        City city = cityService.createOrGetCity(id);
        CityWeatherDTO cityWeatherDTO = weatherService.getCityWeatherDetails(city,daysInPast,daysInFuture);
        return ResponseEntity.ok(cityWeatherDTO);
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

    @GetMapping("/homepage")
    public ResponseEntity<List<CityCurrentWeatherDTO>> getHomePageWeather(@RequestParam String ids){
        try {
            List<CityCurrentWeatherDTO> currentWeatherList = new ArrayList<>();
            List<Long> cityIds = Arrays.stream(ids.split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            for(Long id:cityIds){
                City city = cityService.createOrGetCity(id);
                currentWeatherList.add(weatherService.getCurrentCityWeather(city));
            }
            return ResponseEntity.ok(currentWeatherList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
