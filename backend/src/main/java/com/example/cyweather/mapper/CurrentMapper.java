package com.example.cyweather.mapper;

import com.example.cyweather.DTO.Condition;
import com.example.cyweather.DTO.CurrentDataDTO;
import com.example.cyweather.domain.CurrentData;

public class CurrentMapper {
    public static CurrentDataDTO toEntity(CurrentData data){
        CurrentDataDTO dataDTO = new CurrentDataDTO();

        dataDTO.setTemp_c(data.getCurrentTemp());

        Condition condition = new Condition();
        condition.setText(data.getWeatherCondition());
        condition.setIcon(data.getConditionIconURL());

        dataDTO.setCondition(condition);

        return dataDTO;
    }
}
