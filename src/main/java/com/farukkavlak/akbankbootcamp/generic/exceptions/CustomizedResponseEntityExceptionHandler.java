package com.farukkavlak.akbankbootcamp.generic.exceptions;/*
Created by farukkavlak on 30.05.2023
@author: farukkavlak
@date: 30.05.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.entity.Log;
import com.farukkavlak.akbankbootcamp.generic.enums.LogType;
import com.farukkavlak.akbankbootcamp.generic.response.ExceptionResponse;
import com.farukkavlak.akbankbootcamp.generic.response.RestResponse;
import com.farukkavlak.akbankbootcamp.service.entityService.LogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private LogService logService;

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest){

        Date errorDate = new Date();
        String message = ex.getMessage();
        String description = webRequest.getDescription(false);

        return getResponseEntity(errorDate, message, description, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleBusinessExceptions(BusinessException ex, WebRequest webRequest){

        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String description = webRequest.getDescription(false);

        return getResponseEntity(errorDate, message, description, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleItemNotFoundExceptions(ItemNotFoundException ex, WebRequest webRequest){

        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String description = webRequest.getDescription(false);

        return getResponseEntity(errorDate, message, description, HttpStatus.NOT_FOUND);
    }


    private ResponseEntity<Object> getResponseEntity(Date errorDate, String message, String description, HttpStatus internalServerError) {
        ExceptionResponse genExceptionResponse = new ExceptionResponse(errorDate, message, description);

        RestResponse<ExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessages(message);

        ResponseEntity<RestResponse<ExceptionResponse>> logResponse = new ResponseEntity<>(restResponse, internalServerError);
        logHelper(logResponse);

        return new ResponseEntity<>(restResponse, internalServerError);
    }

    private void logHelper(ResponseEntity<RestResponse<ExceptionResponse>> response) {
        String message = getErrorMessage(response.getBody());
        Log log = new Log(message, LogType.ERROR.toString());
        logService.saveLog(log);
    }

    private String getErrorMessage(Object object){
        String errorMessageBody = "";
        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            errorMessageBody = mapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return errorMessageBody;
    }

}
