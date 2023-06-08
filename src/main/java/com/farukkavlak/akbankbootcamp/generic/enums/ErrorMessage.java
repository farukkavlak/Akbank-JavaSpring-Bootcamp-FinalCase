package com.farukkavlak.akbankbootcamp.generic.enums;/*
Created by farukkavlak on 30.05.2023
@author: farukkavlak
@date: 30.05.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.generic.exceptions.BaseErrorMessage;

public enum ErrorMessage implements BaseErrorMessage{
    ITEM_NOT_FOUND("Item not found!"),
    PARAMETER_CANNOT_BE_NULL("Parameter cannot be null"),
    CITY_ALREADY_ADDED("City already added!"),
    CITY_NOT_FOUND("City not found!"),
    USER_ID_AND_TOKEN_NOT_MATCH("User id and token not match!");


    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
