package com.farukkavlak.akbankbootcamp.entity;/*
Created by farukkavlak on 30.05.2023
@author: farukkavlak
@date: 30.05.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.generic.entity.BaseEntity;
import com.farukkavlak.akbankbootcamp.generic.enums.WeatherType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "forecast_result")
public class ForecastResult extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_seq")
    @SequenceGenerator(name = "result_seq", sequenceName = "result_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_timestamp")
    private String dateTimestamp;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "weather_condition")
    private WeatherType weatherCondition;
    @Column(name = "record_id")
    private Long recordId;

    public ForecastResult(String dateTimestamp, Double temperature, Long recordId, WeatherType weatherCondition) {
        this.dateTimestamp = dateTimestamp;
        this.temperature = temperature;
        this.recordId = recordId;
        this.weatherCondition = weatherCondition;
    }
}
