package com.farukkavlak.akbankbootcamp.mapper;/*
Created by farukkavlak on 30.05.2023
@author: farukkavlak
@date: 30.05.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.dto.UserDto;
import com.farukkavlak.akbankbootcamp.dto.UserLoginReturnDto;
import com.farukkavlak.akbankbootcamp.dto.UserPostRequestDto;
import com.farukkavlak.akbankbootcamp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    //User -> UserDto
    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getCities());
    }

    //UserPostRequestDto -> User
    public User toEntity(UserPostRequestDto userPostRequestDto) {
        User user = new User();
        user.setUsername(userPostRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(userPostRequestDto.getPassword()));
        return user;
    }

    //User -> UserLoginReturnDto
    public UserLoginReturnDto toLoginReturnDto(String token, String username, Long id) {
        return new UserLoginReturnDto(id, username, token);
    }
}
