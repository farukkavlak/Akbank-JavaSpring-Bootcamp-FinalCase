package com.farukkavlak.akbankbootcamp.service.entityService;/*
Created by farukkavlak on 30.05.2023
@author: farukkavlak
@date: 30.05.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.dao.ForecastRecordDao;
import com.farukkavlak.akbankbootcamp.entity.ForecastRecord;
import com.farukkavlak.akbankbootcamp.generic.service.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class ForecastRecordService extends BaseEntityService<ForecastRecord, ForecastRecordDao> {
    public ForecastRecordService(ForecastRecordDao dao) {
        super(dao);
    }
}
