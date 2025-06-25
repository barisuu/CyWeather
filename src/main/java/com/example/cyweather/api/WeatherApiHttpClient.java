package com.example.cyweather.api;

import com.example.cyweather.domain.WeatherData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        String lastUpdated = (String) current.get("last_updated");
        LocalDateTime time = LocalDateTime.parse(lastUpdated, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return new WeatherData(city, weatherConditionText,temp, time);
    }
}
