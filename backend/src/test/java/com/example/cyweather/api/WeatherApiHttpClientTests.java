package com.example.cyweather.api;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

public class WeatherApiHttpClientTests {

    private RestTemplate restTemplate;
    private WeatherApiHttpClient weatherApiHttpClient;

    @BeforeEach
    void setUp(){
        restTemplate = mock(RestTemplate.class);
        RestTemplateBuilder builder = mock(RestTemplateBuilder.class);
        when(builder.build()).thenReturn(restTemplate);

        WeatherApiHttpClient client = new WeatherApiHttpClient(builder);

        client = spy(client);
        doReturn("");

    }
}
