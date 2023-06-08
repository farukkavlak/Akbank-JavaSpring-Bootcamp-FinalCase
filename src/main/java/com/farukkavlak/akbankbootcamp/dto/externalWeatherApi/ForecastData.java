package com.farukkavlak.akbankbootcamp.dto.externalWeatherApi;/*
Created by farukkavlak on 30.05.2023
@author: farukkavlak
@date: 30.05.2023
@project: akbank-bootcamp
*/

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ForecastData {
    @JsonProperty("dt")
    private String dateTimestamp;

    private MainDetails main;
    private List<WeatherDetails> weather;
}
