package com.example.cyweather.controller;

import com.example.cyweather.domain.WeatherData;
import com.example.cyweather.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/current")
    public ResponseEntity<WeatherData> getCurrentWeather(@RequestParam String city){
        try{
            WeatherData data = weatherService.fetchAndSaveCurrentWeather(city);
            return ResponseEntity.ok(data);
        } catch (Exception e){
            //TODO Specific exception handling.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/history")
    //ResponseEntity<List<WeatherData>>
    public void getHistoricalWeather(@RequestParam String city, @RequestParam Integer daysInPast){
        try{
            Object a = weatherService.getHistoricalWeather(city,daysInPast);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
