package com.farukkavlak.akbankbootcamp.generic.entity;/*
Created by farukkavlak on 30.05.2023
@author: farukkavlak
@date: 30.05.2023
@project: akbank-bootcamp
*/

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.util.Date;

@Embeddable
@Getter
@Setter
public class BaseAdditionalFields {

    @Column(
            name = "CREATE_DATE",
            updatable = false
    )
    @CreatedDate
    private Date createDate;
    @Column(
            name = "UPDATE_DATE"
    )
    @LastModifiedDate
    private Date updateDate;

    public BaseAdditionalFields() {
    }

}