package com.example.cyweather.controller;

import com.example.cyweather.DTO.CityCurrentWeatherDTO;
import com.example.cyweather.DTO.CityDTO;
import com.example.cyweather.DTO.CityWeatherDTO;
import com.example.cyweather.DTO.CurrentDataDTO;
import com.example.cyweather.domain.City;
import com.example.cyweather.service.CityService;
import com.example.cyweather.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)
public class WeatherControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WeatherService weatherService;

    @MockitoBean
    private CityService cityService;

    @Test
    public void testSearchCities() throws Exception {
        CityDTO cityDTO1 = new CityDTO();
        cityDTO1.setId(1L);
        cityDTO1.setName("Nicosia");
        cityDTO1.setRegion("Nicosia");

        CityDTO cityDTO2 = new CityDTO();
        cityDTO2.setId(2L);
        cityDTO2.setName("Nicosia");
        cityDTO2.setRegion("Sicilia");

        when(cityService.searchCitiesByName(anyString())).thenReturn(List.of(cityDTO1, cityDTO2));

        mockMvc.perform(get("/weather/cities/search")
                        .param("name", "Nicosia"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Nicosia"))
                .andExpect(jsonPath("$[0].region").value("Nicosia"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Nicosia"))
                .andExpect(jsonPath("$[1].region").value("Sicilia"));

    }

    @Test
    public void testCityDetails() throws Exception {
        Long cityId = 1L;
        int daysInPast = 3;
        int daysInFuture = 5;

        City mockCity = new City(cityId, "Nicosia", "Nicosia", "Cyprus", 10D, 10D);

        CityWeatherDTO mockDTO = new CityWeatherDTO();
        mockDTO.setCity(mockCity);

        when(cityService.createOrGetCity(cityId)).thenReturn(mockCity);
        when(weatherService.getCityWeatherDetails(mockCity, daysInPast, daysInFuture))
                .thenReturn(mockDTO);

        mockMvc.perform(get("/weather/cities/{id}", cityId)
                .param("daysInPast", String.valueOf(daysInPast))
                .param("daysInFuture", String.valueOf(daysInFuture)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.city.name").value("Nicosia"));
    }

    @Test
    void testInitializeApp() throws Exception {
        List<String> initialCityNames = List.of(
                "Nicosia-Nicosia-Cyprus",
                "Girne-Kyrenia-Cyprus",
                "Magusa-Famagusta-Cyprus"
        );

        for (int i = 0; i < initialCityNames.size(); i++) {
            String cityName = initialCityNames.get(i);
            CityDTO cityDTO = new CityDTO();
            cityDTO.setId((long) (i + 1));
            when(cityService.searchCitiesByName(cityName)).thenReturn(List.of(cityDTO));
        }


        mockMvc.perform(post("/weather/init"))
                .andExpect(status().isOk());

        // Verify searchCitiesByName called for each city name
        for (String cityName : initialCityNames) {
            verify(cityService).searchCitiesByName(cityName);
        }

        // Verify createOrGetCity called with expected IDs
        verify(cityService).createOrGetCity(1L);
        verify(cityService).createOrGetCity(2L);
        verify(cityService).createOrGetCity(3L);
    }

    @Test
    void testGetHomePage() throws Exception{
        List<Long> cityIds = List.of(1L,2L,3L);

        for (Long id : cityIds) {
            City city = new City(id, "City" + id, "Region", "Country", 0.0, 0.0);
            when(cityService.createOrGetCity(id)).thenReturn(city);

            CityCurrentWeatherDTO currentWeatherDTO = new CityCurrentWeatherDTO();
            CityDTO cityDTO = new CityDTO();
            CurrentDataDTO currentDataDTO = new CurrentDataDTO();
            cityDTO.setId(id);
            currentDataDTO.setTemp_c(25D);
            currentWeatherDTO.setCurrent(currentDataDTO);
            currentWeatherDTO.setCity(cityDTO);
            when(weatherService.getCurrentCityWeather(city)).thenReturn(currentWeatherDTO);
        }

        mockMvc.perform(get("/weather/homepage")
                .param("ids","1,2,3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(cityIds.size()));
    }

    @Test
    void testGetHomePageException() throws Exception {
        when(cityService.createOrGetCity(anyLong())).thenThrow(new RuntimeException("Test exception"));

        mockMvc.perform(get("/weather/homepage")
                        .param("ids", "1,2,3"))
                .andExpect(status().isInternalServerError());
    }

}
