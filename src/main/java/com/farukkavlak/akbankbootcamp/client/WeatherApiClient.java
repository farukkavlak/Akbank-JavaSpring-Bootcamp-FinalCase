package com.farukkavlak.akbankbootcamp.client;/*
Created by farukkavlak on 7.06.2023
@author: farukkavlak
@date: 7.06.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.dto.externalWeatherApi.Coordinates;
import com.farukkavlak.akbankbootcamp.dto.externalWeatherApi.WeatherForecast;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Feign client to use openweatherapi
@FeignClient(name = "weather-api", url = "${openweatherapi.baseurl}")
@ComponentScan
public interface WeatherApiClient {
    //Get locations from city input by using openweatherapi
    @GetMapping("/geo/1.0/direct")
    Coordinates[] getCoordinates(@RequestParam("q") String city, @RequestParam("limit") int limit, @RequestParam("appid") String appId);

    //Get 5 days weather forecast from coordinates by using openweatherapi
    @GetMapping("/data/2.5/forecast")
    WeatherForecast getWeatherByCoordinates(@RequestParam("lat") String lat, @RequestParam("lon") String lon, @RequestParam("appid") String appId);
}
