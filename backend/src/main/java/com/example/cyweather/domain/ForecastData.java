package com.example.cyweather.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ForecastData extends WeatherData{

    private Double avgTempC;
    private Double maxTempC;
    private Double minTempC;

    @Enumerated(EnumType.STRING)
    private ForecastDataType type;

    public ForecastData() {
    }

    public ForecastData(City city, String weatherCondition, String conditionIconURL, LocalDateTime dateTime,
                        Double avgTempC, Double maxTempC, Double minTempC, ForecastDataType type)
    {
        super(city, weatherCondition, conditionIconURL, dateTime);
        this.avgTempC = avgTempC;
        this.maxTempC = maxTempC;
        this.minTempC = minTempC;
        this.type = type;
    }

    public Double getAvgTempC() {
        return avgTempC;
    }

    public void setAvgTempC(Double avgTempC) {
        this.avgTempC = avgTempC;
    }

    public Double getMaxTempC() {
        return maxTempC;
    }

    public void setMaxTempC(Double maxTempC) {
        this.maxTempC = maxTempC;
    }

    public Double getMinTempC() {
        return minTempC;
    }

    public void setMinTempC(Double minTempC) {
        this.minTempC = minTempC;
    }
}
