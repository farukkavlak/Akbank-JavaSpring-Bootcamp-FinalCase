package com.farukkavlak.akbankbootcamp.service.entityService;/*
Created by farukkavlak on 7.06.2023
@author: farukkavlak
@date: 7.06.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.dao.LogDao;
import com.farukkavlak.akbankbootcamp.entity.Log;
import com.farukkavlak.akbankbootcamp.generic.service.BaseEntityService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Service
@Slf4j
public class LogService extends BaseEntityService<Log,LogDao>{
    public LogService(LogDao dao) {
        super(dao);
    }

    public void saveLog(Log logEntity){
        //According to the logType, it could be "INFO", "ERROR", "WARN", "DEBUG".
        this.save(logEntity);
        try {
            Method logMethod = Logger.class.getMethod(logEntity.getLogType().toString().toLowerCase(), String.class);
            logMethod.invoke(log, logEntity.getMessage());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
