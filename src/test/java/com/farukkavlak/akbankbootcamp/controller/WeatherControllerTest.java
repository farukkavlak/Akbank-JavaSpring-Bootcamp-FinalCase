package com.farukkavlak.akbankbootcamp.controller;/*
Created by farukkavlak on 8.06.2023
@author: farukkavlak
@date: 8.06.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.AkbankBootcampApplication;
import com.farukkavlak.akbankbootcamp.config.H2TestProfileJPAConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {AkbankBootcampApplication.class, H2TestProfileJPAConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WeatherControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private static final String basePath = "/api/v1/weathers";
    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void testGetWeatherWithUserId() throws Exception {
        MvcResult result = mockMvc.perform(get(basePath)
                        .param("city", "Ankara")
                        .param("userId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testGetWeatherWithoutUserId() throws Exception {
        MvcResult result = mockMvc.perform(get(basePath)
                        .param("city", "Ankara")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }


}
