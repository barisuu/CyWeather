package com.example.cyweather.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class CurrentData extends WeatherData {

    private Double currentTemp;

    public CurrentData() {
    }

    public CurrentData(City city, String weatherCondition, String conditionIconURL, LocalDateTime dateTime, Double currentTemp) {
        super(city, weatherCondition, conditionIconURL, dateTime);
        this.currentTemp = currentTemp;
    }

    public Double getCurrentTemp() {
        return currentTemp;
    }

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "city=" + city +
                ", weathercondition=" + weatherCondition +
                ", temp='" + currentTemp + '\'' +
                ", time='" + dateTime + '\'' +
                '}';
    }
}
