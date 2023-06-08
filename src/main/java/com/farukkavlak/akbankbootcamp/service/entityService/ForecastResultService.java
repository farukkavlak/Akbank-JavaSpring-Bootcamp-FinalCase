package com.farukkavlak.akbankbootcamp.service.entityService;/*
Created by farukkavlak on 30.05.2023
@author: farukkavlak
@date: 30.05.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.dao.ForecastResultDao;
import com.farukkavlak.akbankbootcamp.entity.ForecastResult;
import com.farukkavlak.akbankbootcamp.generic.service.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class ForecastResultService extends BaseEntityService<ForecastResult, ForecastResultDao> {
    public ForecastResultService(ForecastResultDao dao) {
        super(dao);
    }
}
