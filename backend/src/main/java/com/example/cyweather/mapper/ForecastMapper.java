package com.example.cyweather.mapper;

import com.example.cyweather.DTO.Condition;
import com.example.cyweather.DTO.Day;
import com.example.cyweather.DTO.ForecastDataDTO;
import com.example.cyweather.domain.ForecastData;

public class ForecastMapper {
    public static ForecastDataDTO toEntity(ForecastData data)
    {
        ForecastDataDTO forecastDTO = new ForecastDataDTO();
        forecastDTO.setDate(data.getDateTime().toString());

        Condition condition = new Condition();
        condition.setIcon(data.getConditionIconURL());
        condition.setText(data.getWeatherCondition());

        Day day = new Day();
        day.setMaxtemp_c(data.getMaxTempC());
        day.setAvgtemp_c(data.getAvgTempC());
        day.setMintemp_c(data.getMinTempC());
        day.setCondition(condition);

        forecastDTO.setDay(day);

        return forecastDTO;
    }
}
