package com.farukkavlak.akbankbootcamp.mapper;/*
Created by farukkavlak on 30.05.2023
@author: farukkavlak
@date: 30.05.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.dto.ForecastRecordDto;
import com.farukkavlak.akbankbootcamp.dto.ForecastResultDto;
import com.farukkavlak.akbankbootcamp.dto.UserPostRequestDto;
import com.farukkavlak.akbankbootcamp.dto.externalWeatherApi.ForecastData;
import com.farukkavlak.akbankbootcamp.dto.externalWeatherApi.WeatherForecast;
import com.farukkavlak.akbankbootcamp.entity.ForecastResult;
import com.farukkavlak.akbankbootcamp.entity.User;
import com.farukkavlak.akbankbootcamp.generic.enums.WeatherType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WeatherMapper {

    public List<ForecastResult> mapWeatherForecastToForecastResult(WeatherForecast weatherForecast, Long recordId) {
        List<ForecastData> forecastDataList = weatherForecast.getList();
        List<ForecastResult> forecastResultList = new ArrayList<>();
        for (ForecastData forecastData : forecastDataList) {
            ForecastResult forecastResult = new ForecastResult(forecastData.getDateTimestamp(),forecastData.getMain().getTempInCelsius(),recordId, WeatherType.valueOf(forecastData.getWeather().get(0).getMain()));
            forecastResultList.add(forecastResult);
        }
        return forecastResultList;
    }

    public ForecastRecordDto mapForecastRecordToForecastRecordDto(List<ForecastResult> forecastResultList, String city) {
        ForecastRecordDto forecastRecordDto = new ForecastRecordDto();
        forecastRecordDto.setCity(city);
        forecastRecordDto.setForecastResultList(mapForecastResultListToForecastResultDtoList(forecastResultList));
        return forecastRecordDto;
    }

    public List<ForecastResultDto> mapForecastResultListToForecastResultDtoList(List<ForecastResult> forecastResultList) {
        return forecastResultList.stream().map(forecastResult -> {
            ForecastResultDto forecastResultDto = new ForecastResultDto();
            forecastResultDto.setDateTimestamp(forecastResult.getDateTimestamp());
            forecastResultDto.setTemperature(forecastResult.getTemperature());
            forecastResultDto.setWeatherCondition(forecastResult.getWeatherCondition());
            return forecastResultDto;
        }).collect(Collectors.toList());
    }

}
