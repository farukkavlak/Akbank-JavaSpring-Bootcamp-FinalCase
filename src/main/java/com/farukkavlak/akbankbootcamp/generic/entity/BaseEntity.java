package com.farukkavlak.akbankbootcamp.generic.entity;/*
Created by farukkavlak on 30.05.2023
@author: farukkavlak
@date: 30.05.2023
@project: akbank-bootcamp
*/

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable, Cloneable, BaseModel {
    private static final long serialVersionUID = 1L;
    @Embedded
    private BaseAdditionalFields baseAdditionalFields;

}