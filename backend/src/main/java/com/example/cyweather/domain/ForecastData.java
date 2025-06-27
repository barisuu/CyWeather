package com.example.cyweather.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (!(o instanceof ForecastData that)) return false;

        return city != null && city.equals(that.city)
                && dateTime != null && dateTime.equals(that.dateTime)
                && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                city!=null ? city.getId() : null,
                dateTime,
                type
        );
    }

    public Double getAvgTempC() {
        return avgTempC;
    }

    public Double getMaxTempC() {
        return maxTempC;
    }

    public Double getMinTempC() {
        return minTempC;
    }
}
