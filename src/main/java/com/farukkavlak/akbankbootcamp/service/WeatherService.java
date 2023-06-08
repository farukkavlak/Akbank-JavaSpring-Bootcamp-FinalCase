package com.farukkavlak.akbankbootcamp.service;/*
Created by farukkavlak on 30.05.2023
@author: farukkavlak
@date: 30.05.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.client.WeatherApiClient;
import com.farukkavlak.akbankbootcamp.dto.ForecastRecordDto;
import com.farukkavlak.akbankbootcamp.dto.externalWeatherApi.Coordinates;
import com.farukkavlak.akbankbootcamp.dto.externalWeatherApi.WeatherForecast;
import com.farukkavlak.akbankbootcamp.entity.ForecastRecord;
import com.farukkavlak.akbankbootcamp.entity.ForecastResult;
import com.farukkavlak.akbankbootcamp.entity.Log;
import com.farukkavlak.akbankbootcamp.generic.enums.LogType;
import com.farukkavlak.akbankbootcamp.mapper.WeatherMapper;
import com.farukkavlak.akbankbootcamp.service.entityService.ForecastRecordService;
import com.farukkavlak.akbankbootcamp.service.entityService.ForecastResultService;
import com.farukkavlak.akbankbootcamp.service.entityService.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {
    //Get openweatherapi.appid from application.properties
    @Value("${openweatherapi.appid}")
    private String appId;
    private final WeatherApiClient weatherApiClient;
    private final ForecastRecordService forecastRecordService;
    private final ForecastResultService forecastResultService;
    private final WeatherMapper weatherMapper;
    private final LogService logService;

    public ForecastRecordDto getWeather(Long userId, String city) {
        Coordinates[] coordinates = getCoordinates(city);
        String lat = coordinates[0].getLat();
        String lon = coordinates[0].getLon();

        logHelper(userId, city);

        return getWeatherByCoordinates(lat, lon, city,userId);
    }

    private Coordinates[] getCoordinates(String city) {
        return weatherApiClient.getCoordinates(city,1,appId);
    }
    private ForecastRecordDto getWeatherByCoordinates(String lat, String lon,String city,Long userId) {
        WeatherForecast weatherForecast = weatherApiClient.getWeatherByCoordinates(lat,lon,appId);

        checkIsReturnValid(weatherForecast);

        ForecastRecord forecastRecord = new ForecastRecord(city, userId);
        forecastRecordService.save(forecastRecord);

        List<ForecastResult> resultList = this.weatherMapper.mapWeatherForecastToForecastResult(weatherForecast, forecastRecord.getId());
        forecastResultService.saveAll(resultList);


        return weatherMapper.mapForecastRecordToForecastRecordDto(resultList, city);
    }

    private static void checkIsReturnValid(WeatherForecast weatherForecast) {
        if (weatherForecast == null) {
            throw new RuntimeException("Weather forecast is null");
        }
        if (weatherForecast.getList() == null) {
            throw new RuntimeException("Weather forecast data is null");
        }
        if (weatherForecast.getList().size() == 0) {
            throw new RuntimeException("Weather forecast data is empty");
        }
    }
    private void logHelper(Long userId, String city) {
        String message = "Weather forecast requested for " + city + " by user " + userId;
        if (userId == null) {
            message = "Weather forecast requested for " + city + " by anonymous user";
        }
        Log log = new Log(message, LogType.INFO.name());
        logService.saveLog(log);
    }
}
