package com.farukkavlak.akbankbootcamp.entity;/*
Created by farukkavlak on 30.05.2023
@author: farukkavlak
@date: 30.05.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.generic.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "forecast_record")
public class ForecastRecord extends BaseEntity {

    @Id
    @SequenceGenerator(name = "forecast_record_id_seq", sequenceName = "forecast_record_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "forecast_record_id_seq",strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "user_id")
    private Long userId;

    public ForecastRecord(String city, Long userId) {
        this.city = city;
        this.userId = userId;
    }
}
