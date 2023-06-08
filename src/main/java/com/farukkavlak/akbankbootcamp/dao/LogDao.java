package com.farukkavlak.akbankbootcamp.dao;/*
Created by farukkavlak on 7.06.2023
@author: farukkavlak
@date: 7.06.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDao extends JpaRepository<Log,Long>{

}
