package com.farukkavlak.akbankbootcamp.dto;/*
Created by farukkavlak on 30.05.2023
@author: farukkavlak
@date: 30.05.2023
@project: akbank-bootcamp
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForecastRecordDto {
    String city;
    List<ForecastResultDto> forecastResultList;
}
