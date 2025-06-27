package com.example.cyweather.api;

import com.example.cyweather.DTO.*;
import com.example.cyweather.domain.*;
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
    public CurrentData fetchCurrentWeather(City city) {
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

        Current currentData = response.getCurrent();
        Double temp= currentData.getTemp_c();

        Condition condition = currentData.getCondition();
        String weatherConditionText = condition.getText();
        String iconURL = condition.getIcon();

        //Using localdatetime.now instead of the API's last updated as lastupdated time occasionally displays the wrong
        //time.
        return new CurrentData(city, weatherConditionText,iconURL,LocalDateTime.now(),temp);
    }

    @Override
    public List<ForecastData> fetchHistoricalWeather(City city, LocalDate dt){
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.weatherapi.com")
                .path("/v1/history.json")
                .queryParam("key",apiKey)
                .queryParam("q","id:"+city.getId())
                .queryParam("dt",dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .queryParam("end_dt",LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build()
                .toUriString();

        ForecastResponse response = restTemplate.getForObject(url, ForecastResponse.class);

        if(response==null){
            throw new RuntimeException("Invalid response from API for historical data");
        }
        return getForecastData(city,response);
    }

    @Override
    public List<ForecastData> fetchForecastWeather(City city, Integer days){
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.weatherapi.com")
                .path("/v1/forecast.json")
                .queryParam("key",apiKey)
                .queryParam("q","id:"+city.getId())
                .queryParam("days",days+1)
                .queryParam("end_dt",LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build()
                .toUriString();

        ForecastResponse response = restTemplate.getForObject(url, ForecastResponse.class);

        if(response==null){
            throw new RuntimeException("Invalid response from API for forecast data");
        }
        return getForecastData(city, response);
    }

    private static List<ForecastData> getForecastData(City city, ForecastResponse response) {
        List<ForecastData> forecastDays = new ArrayList<>();
        for(ForecastDay day: response.getForecast().getForecastday()){
            LocalDateTime date = LocalDate.parse(day.getDate()).atStartOfDay();
            Double avgTemp = day.getDay().getAvgtemp_c();
            Double maxTemp = day.getDay().getMaxtemp_c();
            Double minTemp = day.getDay().getMintemp_c();
            String condition = day.getDay().getCondition().getText();
            String iconURL = day.getDay().getCondition().getIcon();
            ForecastData temp = new ForecastData(city,condition,iconURL,date,avgTemp,
                    maxTemp,minTemp, ForecastDataType.FORECAST);
            forecastDays.add(temp);
        }
        return forecastDays;
    }
}
