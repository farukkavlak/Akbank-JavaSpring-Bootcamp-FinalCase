package com.farukkavlak.akbankbootcamp.service.entityService;/*
Created by farukkavlak on 30.05.2023
@author: farukkavlak
@date: 30.05.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.dto.*;
import com.farukkavlak.akbankbootcamp.entity.Log;
import com.farukkavlak.akbankbootcamp.generic.enums.ErrorMessage;
import com.farukkavlak.akbankbootcamp.generic.enums.LogType;
import com.farukkavlak.akbankbootcamp.generic.exceptions.BusinessException;
import com.farukkavlak.akbankbootcamp.jwtConfig.JwtService;
import com.farukkavlak.akbankbootcamp.dao.UserDao;
import com.farukkavlak.akbankbootcamp.entity.User;
import com.farukkavlak.akbankbootcamp.generic.service.BaseEntityService;
import com.farukkavlak.akbankbootcamp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends BaseEntityService<User, UserDao> {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private LogService logService;

    public UserService(UserDao dao) {
        super(dao);
    }

    public UserDto save(UserPostRequestDto userPostRequestDto) {
        User user = userMapper.toEntity(userPostRequestDto);
        user = this.save(user);
        logHelper("User saved with id: " + user.getId());
        return userMapper.toDto(user);
    }
    public UserLoginReturnDto login(UserLoginRequestDto userLoginRequestDto) {
        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequestDto.getUsername(), userLoginRequestDto.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(userLoginRequestDto.getUsername(), this.findByUsername(userLoginRequestDto.getUsername()).getId());
            return userMapper.toLoginReturnDto(token, userLoginRequestDto.getUsername(), this.findByUsername(userLoginRequestDto.getUsername()).getId());
        }else {
            throw new BusinessException(ErrorMessage.ITEM_NOT_FOUND);
        }
    }

    public UserDto addCity(Long userId, String city) {
        User user = this.findById(userId);
        checkIsCityAlreadyAdded(city, user);
        user.getCities().add(city);
        this.save(user);
        //Log
        logHelper(city + " added to user with id: " + userId);
        return userMapper.toDto(user);
    }



    public UserDto deleteCity(Long userId, String city) {
        //Check there is user with userId
        User user = this.findById(userId);
        checkIsUserHasCity(city, user);
        //Delete city from user
        user.getCities().remove(city);
        //Save user
        this.save(user);
        logHelper("City: " + city + " deleted from user with id: " + userId);
        return userMapper.toDto(user);
    }

    public List<String> getCities(Long userId) {
        //Check there is user with userId
        User user = this.findById(userId);
        //Get cities from user
        return user.getCities();
    }


    //Helper methods
    private User findByUsername(String username) {
        return this.getDao().findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
    private static void checkIsCityAlreadyAdded(String city, User user) {
        Optional<String> cityOptional = user.getCities().stream().filter(c -> c.equals(city)).findFirst();
        if (cityOptional.isPresent()) {
            throw new BusinessException(ErrorMessage.CITY_ALREADY_ADDED);
        }
    }

    private static void checkIsUserHasCity(String city, User user) {
        //Check if the city is already added
        Optional<String> cityOptional = user.getCities().stream().filter(c -> c.equals(city)).findFirst();
        if (cityOptional.isEmpty()) {
            throw new BusinessException(ErrorMessage.CITY_NOT_FOUND);
        }
    }
    private void logHelper(String message) {
        Log logEntity = new Log();
        logEntity.setMessage(message);
        logEntity.setLogType(LogType.INFO.name());
        logService.saveLog(logEntity);
    }
}
