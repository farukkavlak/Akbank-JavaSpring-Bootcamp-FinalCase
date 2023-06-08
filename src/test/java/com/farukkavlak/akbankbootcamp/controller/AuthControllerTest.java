package com.farukkavlak.akbankbootcamp.controller;/*
Created by farukkavlak on 8.06.2023
@author: farukkavlak
@date: 8.06.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.AkbankBootcampApplication;
import com.farukkavlak.akbankbootcamp.config.H2TestProfileJPAConfig;
import com.farukkavlak.akbankbootcamp.dto.UserLoginRequestDto;
import com.farukkavlak.akbankbootcamp.dto.UserLoginReturnDto;
import com.farukkavlak.akbankbootcamp.dto.UserPostRequestDto;
import com.farukkavlak.akbankbootcamp.generic.enums.ErrorMessage;
import com.farukkavlak.akbankbootcamp.generic.response.RestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {AkbankBootcampApplication.class, H2TestProfileJPAConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private static final String basePath = "/api/v1/auth";
    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    @Order(1)
    void testRegister() throws Exception {
        UserPostRequestDto userPostRequestDto = new UserPostRequestDto("faruk", "123456");
        String body = objectMapper.writeValueAsString(userPostRequestDto);
        MvcResult result = mockMvc.perform(post(basePath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(2)
    void testLoginSuccessful() throws Exception {

        UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto("faruk", "123456");
        String bodyLogin = objectMapper.writeValueAsString(userLoginRequestDto);
        MvcResult resultLogin = mockMvc.perform(post(basePath + "/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyLogin))
                .andExpect(status().isOk())
                .andReturn();
        String responseJson = resultLogin.getResponse().getContentAsString();

        RestResponse<?> response = objectMapper.readValue(responseJson, RestResponse.class);
        UserLoginReturnDto userLoginReturnDto = objectMapper.convertValue(response.getData(), UserLoginReturnDto.class);

        assertNotNull(userLoginReturnDto);
        assertNotNull(userLoginReturnDto.getToken());
        assertNotNull(userLoginReturnDto.getId());
    }

    @Test
    @Order(3)
    void testLoginBadCredentials() throws Exception {

        UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto("farukkavlakk", "1234567");
        String bodyLogin = objectMapper.writeValueAsString(userLoginRequestDto);
        MvcResult resultLogin = mockMvc.perform(post(basePath + "/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyLogin))
                .andExpect(status().isInternalServerError())
                .andReturn();
        String responseJson = resultLogin.getResponse().getContentAsString();
        RestResponse<?> response = objectMapper.readValue(responseJson, RestResponse.class);

        String errorMessage = response.getMessages();
        assertNotNull(errorMessage);
        assertEquals("Bad credentials", errorMessage);
    }



}
