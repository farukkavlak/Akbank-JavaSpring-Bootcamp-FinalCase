package com.farukkavlak.akbankbootcamp.dto.externalWeatherApi;/*
Created by farukkavlak on 30.05.2023
@author: farukkavlak
@date: 30.05.2023
@project: akbank-bootcamp
*/

import lombok.Data;


@Data
public class MainDetails {
    private double temp;


    // Temperature conversion methods
    public double getTempInCelsius() {
        return Math.round((temp - 273.15) * 100.0) / 100.0;
    }

    private double getTempInFahrenheit() {
        return Math.round((temp * 9 / 5 - 459.67) * 100.0) / 100.0;
    }
}
