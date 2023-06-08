package com.farukkavlak.akbankbootcamp.controller;/*
Created by farukkavlak on 30.05.2023
@author: farukkavlak
@date: 30.05.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.dto.UserCitiesSaveRequestDto;
import com.farukkavlak.akbankbootcamp.dto.UserDto;
import com.farukkavlak.akbankbootcamp.dto.UserLoginRequestDto;
import com.farukkavlak.akbankbootcamp.dto.UserPostRequestDto;
import com.farukkavlak.akbankbootcamp.generic.response.RestResponse;
import com.farukkavlak.akbankbootcamp.service.entityService.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    @Autowired
    private UserService userService;
    @Operation( tags = "Auth-Controller",
            summary = "Login",
            description = "### Requirements\n" +
                    "- *Credentials should be valid*\n\n" +
                    "### Sample Input\n\n ```\n" +
                    "{\n" +
                    "  \"username\": \"farukkavlak\",\n" +
                    "  \"password\": \"123456\"\n" +
                    "}" + "\n```\n\n" +
                    "### Sample Output\n\n ```\n" + "{\n" +
                    "  \"data\": {\n" +
                    "    \"id\": 1,\n" +
                    "    \"username\": \"farukkavlak\",\n" +
                    "    \"token\": \"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmYXJ1a2thdmxhayIsImlhdCI6MTYyNTYwNjQ5MCwiZXhwIjoxN\",\n" +
                    "  },\n" +
                    "  \"responseDate\": \"2022-07-08T00:24:10.701+00:00\",\n" +
                    "  \"messages\": null,\n" +
                    "  \"success\": true\n" +
                    "}" + "\n```\n\n",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserLoginRequestDto.class),
                    examples = @ExampleObject(value = "{\n" +
                            "  \"username\": \"farukkavlak\",\n" +
                            "  \"password\": \"123456\"\n" +
                            "}"))))
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        return ResponseEntity.ok(RestResponse.of(userService.login(userLoginRequestDto)));
    }

    @Operation( tags = "Auth-Controller",
            summary = "Register",
            description = "### Requirements\n" +
                    "- *Username should be unique*\n\n" +
                    "### Sample Input\n\n ```\n" +
                    "{\n" +
                    "  \"username\": \"farukkavlak\",\n" +
                    "  \"password\": \"123456\"\n" +
                    "}" + "\n```\n\n" +
                    "### Sample Output\n\n ```\n" + "{\n" +
                    "  \"data\": {\n" +
                    "    \"id\": 1,\n" +
                    "    \"username\": \"farukkavlak\",\n" +
                    "    \"cities\": []\n" +
                    "  },\n" +
                    "  \"responseDate\": \"2022-07-08T00:24:10.701+00:00\",\n" +
                    "  \"messages\": null,\n" +
                    "  \"success\": true\n" +
                    "}" + "\n```\n\n",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserPostRequestDto.class),
                    examples = @ExampleObject(value = "{\n" +
                            "  \"username\": \"farukkavlak\",\n" +
                            "  \"password\": \"123456\"\n" +
                            "}"))))
    @PostMapping()
    public ResponseEntity register(@RequestBody UserPostRequestDto userPostRequestDto) {
        return ResponseEntity.ok(RestResponse.of(userService.save(userPostRequestDto)));
    }
}
