package com.example.cyweather.api;

import com.example.cyweather.DTO.*;
import com.example.cyweather.domain.City;
import com.example.cyweather.domain.WeatherData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherApiHttpClient implements WeatherApiClient {

    private final RestTemplate restTemplate;

    @Value("${weatherapi.key}")
    private String apiKey;

    public WeatherApiHttpClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public WeatherData fetchCurrentWeather(City city) {
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.weatherapi.com")
                .path("/v1/current.json")
                .queryParam("key",apiKey)
                .queryParam("q","id:"+city.getId())
                .build()
                .toUriString();

        CurrentResponse response = restTemplate.getForObject(url, CurrentResponse.class);

        if(response == null){
            throw new RuntimeException("Invalid response from WeatherAPI");
        }

        Current current = response.getCurrent();
        Double temp= current.getTemp_c();

        Condition condition = current.getCondition();
        String weatherConditionText = condition.getText();

        //Using localdatetime.now instead of the API's last updated as lastupdated time occasionally displays the wrong
        //time.
        return new WeatherData(city, weatherConditionText,temp, LocalDateTime.now());
    }

    @Override
    public List<WeatherData> fetchHistoricalWeather(City city, LocalDate dt){
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.weatherapi.com")
                .path("/v1/history.json")
                .queryParam("key",apiKey)
                .queryParam("q","id:"+city.getId())
                .queryParam("dt",dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .queryParam("end_dt",LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build()
                .toUriString();

        ForecastResponse response = restTemplate.getForObject(url, ForecastResponse.class);

        if(response==null){
            throw new RuntimeException("Invalid response from API for historical data");
        }
        List<WeatherData> pastDays = new ArrayList<>();
        for(ForecastDay day: response.getForecast().getForecastday()){
            LocalDate date = LocalDate.parse(day.getDate(),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Double avgTemp = day.getDay().getAvgtemp_c();
            String condition = day.getDay().getCondition().getText();
            WeatherData temp = new WeatherData(city,condition,avgTemp,date.atStartOfDay());
            pastDays.add(temp);
        }
        return pastDays;
    }
}
