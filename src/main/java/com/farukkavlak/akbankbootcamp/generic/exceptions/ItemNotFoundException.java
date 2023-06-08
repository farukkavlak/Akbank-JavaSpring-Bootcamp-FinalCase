package com.farukkavlak.akbankbootcamp.generic.exceptions;/*
Created by farukkavlak on 7.06.2023
@author: farukkavlak
@date: 7.06.2023
@project: akbank-bootcamp
*/

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends BusinessException{
    public ItemNotFoundException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
