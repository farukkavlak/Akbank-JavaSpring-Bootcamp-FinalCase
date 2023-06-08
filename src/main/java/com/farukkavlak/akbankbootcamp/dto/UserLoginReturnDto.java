package com.farukkavlak.akbankbootcamp.dto;/*
Created by farukkavlak on 6.06.2023
@author: farukkavlak
@date: 6.06.2023
@project: akbank-bootcamp
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginReturnDto {
    private Long id;
    private String username;
    private String token;


}
