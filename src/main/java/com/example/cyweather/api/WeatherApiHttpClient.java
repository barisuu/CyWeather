package com.example.cyweather.api;

import com.example.cyweather.DTO.ForecastDay;
import com.example.cyweather.DTO.ForecastResponse;
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
import java.util.Map;

@Service
public class WeatherApiHttpClient implements WeatherApiClient {

    private final RestTemplate restTemplate;

    @Value("${weatherapi.key}")
    private String apiKey;

    public WeatherApiHttpClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public WeatherData fetchCurrentWeather(String city) {
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.weatherapi.com")
                .path("/v1/current.json")
                .queryParam("key",apiKey)
                .queryParam("q",city)
                .build()
                .toUriString();

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if(response == null){
            throw new RuntimeException("Invalid response from WeatherAPI");
        }

        Map<String,Object> current = (Map<String,Object>) response.get("current");
        Double temp=(Double) current.get("temp_c");

        Map<String, Object> condition = (Map<String, Object>) current.get("condition");
        String weatherConditionText = (String) condition.get("text");

        //Using localdatetime.now instead of the API's last updated as lastupdated time occasionally displays the wrong
        //time.
        return new WeatherData(city, weatherConditionText,temp, LocalDateTime.now());
    }

    @Override
    public List<WeatherData> fetchHistoricalWeather(String city, LocalDate dt){
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.weatherapi.com")
                .path("/v1/history.json")
                .queryParam("key",apiKey)
                .queryParam("q",city)
                .queryParam("dt",dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build()
                .toUriString();

        ForecastResponse response = restTemplate.getForObject(url, ForecastResponse.class);

        if(response==null){
            throw new RuntimeException("Invalid response from API for historical data");
        }

        List<WeatherData> pastDays = new ArrayList<>();

        for(ForecastDay day: response.getForecast().getForecastday()){
            String date = day.getDate();
            Double avgTemp = day.getDay().getAvgtemp_c();
            String condition = day.getDay().getCondition().getText();
            WeatherData temp = new WeatherData(city,condition,avgTemp,LocalDateTime.parse(date));
            pastDays.add(temp);
        }
        return pastDays;
    }
}
