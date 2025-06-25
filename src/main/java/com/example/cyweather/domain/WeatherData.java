package com.example.cyweather.domain;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"city","time"})
)
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    private String weathercondition;
    private Double temp;
    private LocalDateTime time;

    public WeatherData() {
    }

    public WeatherData(City city, String weathercondition, Double temp, LocalDateTime time) {
        this.city = city;
        this.weathercondition = weathercondition;
        this.temp = temp;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getWeathercondition() {
        return weathercondition;
    }

    public void setWeathercondition(String weathercondition) {
        this.weathercondition = weathercondition;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "city=" + city +
                ", weathercondition=" + weathercondition +
                ", temp='" + temp + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
