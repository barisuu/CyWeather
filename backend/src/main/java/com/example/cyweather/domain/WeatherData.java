package com.example.cyweather.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"city","dateTime"})
)
public abstract class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    protected City city;
    protected String weatherCondition;
    protected String conditionIconURL;
    protected LocalDateTime dateTime;

    public WeatherData(City city, String weatherCondition, String conditionIconURL, LocalDateTime dateTime) {
        this.city = city;
        this.weatherCondition = weatherCondition;
        this.conditionIconURL = conditionIconURL;
        this.dateTime = dateTime;
    }

    public WeatherData() {

    }

    public Long getId() {
        return id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public String getConditionIconURL() {
        return conditionIconURL;
    }

    public void setConditionIconURL(String conditionIconURL) {
        this.conditionIconURL = conditionIconURL;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
