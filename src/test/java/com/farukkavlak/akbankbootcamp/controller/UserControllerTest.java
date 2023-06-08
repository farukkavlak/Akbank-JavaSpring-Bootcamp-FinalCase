package com.farukkavlak.akbankbootcamp.controller;

/*
Created by farukkavlak on 8.06.2023
@author: farukkavlak
@date: 8.06.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.AkbankBootcampApplication;
import com.farukkavlak.akbankbootcamp.config.H2TestProfileJPAConfig;
import com.farukkavlak.akbankbootcamp.dto.UserCitiesSaveRequestDto;
import com.farukkavlak.akbankbootcamp.generic.enums.ErrorMessage;
import com.farukkavlak.akbankbootcamp.generic.exceptions.BaseErrorMessage;
import com.farukkavlak.akbankbootcamp.generic.response.RestResponse;
import com.farukkavlak.akbankbootcamp.jwtConfig.JwtService;
import com.farukkavlak.akbankbootcamp.service.entityService.UserService;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {AkbankBootcampApplication.class, H2TestProfileJPAConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private static final String basePath = "/api/v1/users";
    @Autowired
    private WebApplicationContext context;

    private static String bearerToken;
    private static String testUsername = "farukkavlak";
    private static String testPassword = "1234567";
    private static Long testUserId = 1L;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    @Order(1)
    void testSaveCity() throws Exception {
        // Register and login to get the JWT token
        String userBody = "{\"username\":\"" + testUsername + "\",\"password\":\"" + testPassword + "\"}";
        mockMvc.perform(post("/api/v1/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userBody))
                .andExpect(status().isOk())
                .andReturn();
        MvcResult loginResult = mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userBody))
                .andExpect(status().isOk())
                .andReturn();
        String loginResponseJson = loginResult.getResponse().getContentAsString();
        //loginResponseJson structure -> ""data":{"id":id,"username":"username","token":"token"
        bearerToken = "Bearer " + objectMapper.readTree(loginResponseJson).at("/data/token").asText();
        testUserId = Long.valueOf(objectMapper.readTree(loginResponseJson).at("/data/id").asText());

        UserCitiesSaveRequestDto userCitiesSaveRequestDto = new UserCitiesSaveRequestDto("Ankara");
        String body = objectMapper.writeValueAsString(userCitiesSaveRequestDto);

        MvcResult result = mockMvc.perform(post(basePath + "/" + testUserId + "/cities")
                        .header("Authorization", bearerToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn();
        String responseJson = result.getResponse().getContentAsString();
        RestResponse<?> response = objectMapper.readValue(responseJson, RestResponse.class);
        //It should be {id=1, username=farukkavlak, cities=[Ankara]}
        assertNotNull(response.getData());
        assertEquals("{id=" + testUserId + ", username=" + testUsername + ", cities=[" + userCitiesSaveRequestDto.getCity() + "]}", response.getData().toString());
    }

    @Test
    @Order(2)
    void testSaveCityAlreadyAddedError() throws Exception {
        UserCitiesSaveRequestDto userCitiesSaveRequestDto = new UserCitiesSaveRequestDto("Ankara");
        String body = objectMapper.writeValueAsString(userCitiesSaveRequestDto);

        MvcResult result = mockMvc.perform(post(basePath + "/" + testUserId + "/cities")
                        .header("Authorization", bearerToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isInternalServerError())
                .andReturn();
        String responseJson = result.getResponse().getContentAsString();
        RestResponse<?> response = objectMapper.readValue(responseJson, RestResponse.class);

        String errorMesage = response.getMessages();
        assertEquals(ErrorMessage.CITY_ALREADY_ADDED.getMessage(), errorMesage);
    }

    @Test
    @Order(3)
    void testSaveCityUserTryToAddCityToAnotherUser() throws Exception {
        UserCitiesSaveRequestDto userCitiesSaveRequestDto = new UserCitiesSaveRequestDto("Ankara");
        String body = objectMapper.writeValueAsString(userCitiesSaveRequestDto);

        MvcResult result = mockMvc.perform(post(basePath + "/" + 2 + "/cities")
                        .header("Authorization", bearerToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isInternalServerError())
                .andReturn();
        String responseJson = result.getResponse().getContentAsString();
        RestResponse<?> response = objectMapper.readValue(responseJson, RestResponse.class);

        String errorMesage = response.getMessages();
        assertEquals(ErrorMessage.USER_ID_AND_TOKEN_NOT_MATCH.getMessage(), errorMesage);
    }
    @Test
    @Order(4)
    void testListCities() throws Exception {
        MvcResult result = mockMvc.perform(get(basePath + "/" + testUserId + "/cities")
                        .header("Authorization", bearerToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        RestResponse<?> response = objectMapper.readValue(responseJson, RestResponse.class);
        //It should be {id=1, username=farukkavlak, cities=[Ankara]}
        assertNotNull(response.getData());
        assertEquals("[Ankara]", response.getData().toString());
    }
    @Test
    @Order(5)
    void testDeleteCity() throws Exception {


        MvcResult result = mockMvc.perform(delete(basePath + "/" + testUserId + "/cities/Ankara")
                        .header("Authorization", bearerToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        RestResponse<?> response = objectMapper.readValue(responseJson, RestResponse.class);
        //It should be {id=1, username=farukkavlak, cities=[]}
        assertNotNull(response.getData());
        assertEquals("{id=" + testUserId + ", username=" + testUsername + ", cities=[]}", response.getData().toString());
    }
}