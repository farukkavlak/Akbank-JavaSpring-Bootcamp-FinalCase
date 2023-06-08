package com.farukkavlak.akbankbootcamp.entity;/*
Created by farukkavlak on 7.06.2023
@author: farukkavlak
@date: 7.06.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.generic.entity.BaseEntity;
import com.farukkavlak.akbankbootcamp.generic.enums.LogType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "log")
public class Log extends BaseEntity {
    @Id
    @SequenceGenerator(name = "log_id_seq", sequenceName = "log_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "log_id_seq",strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "log_message", length = 1000)
    private String message;
    @Column(name = "log_type")
    private String logType;

    public Log(String message, String info) {
        this.message = message;
        this.logType = info;
    }
}
