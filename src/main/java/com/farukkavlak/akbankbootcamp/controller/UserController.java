package com.farukkavlak.akbankbootcamp.controller;/*
Created by farukkavlak on 6.06.2023
@author: farukkavlak
@date: 6.06.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.dto.UserCitiesSaveRequestDto;
import com.farukkavlak.akbankbootcamp.generic.response.RestResponse;
import com.farukkavlak.akbankbootcamp.jwtConfig.JwtService;
import com.farukkavlak.akbankbootcamp.service.entityService.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    @Operation( tags = "User-Controller",
            summary = "Save city to user",
            description = "### Requirements\n" +
                    "- *Username should be unique*\n " +
                    "### Sample Input\n\n ```\n" +
                    "{\n" +
                    "  \"city\": \"Ankara\"\n" +
                    "}" + "\n```\n\n" +
                    "### Sample Output\n\n ```\n" + "{\n" +
                    "  \"data\": {\n" +
                    "    \"id\": 1,\n" +
                    "    \"username\": \"farukkavlak\",\n" +
                    "    \"cities\": [\n" +
                    "      \"Ankara\",\n" +
                    "      \"Istanbul\"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"responseDate\": \"2022-07-08T00:24:10.701+00:00\",\n" +
                    "  \"messages\": null,\n" +
                    "  \"success\": true\n" +
                    "}" + "\n```\n\n",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserCitiesSaveRequestDto.class),
                    examples = @ExampleObject(value = "{\n" +
                            "  \"city\": \"Ankara\"\n" +
                            "}"))))
    @PostMapping("/{id}/cities")
    public ResponseEntity save(@RequestBody UserCitiesSaveRequestDto dto, @PathVariable Long id, @RequestHeader(required = false, value = "Authorization") String token) {
        jwtService.checkIdAndTokenMatch(id, token.substring(7));
        return ResponseEntity.ok(RestResponse.of(userService.addCity(id,dto.getCity())));
    }

    @Operation( tags = "User-Controller",
            summary = "Delete city from user",
            description = "### Requirements\n" +
                    "- *User should be logged in*\n " +
                    "- *User ID in path variable should be same with logged in user id*\n" +
                    "- *City should be in user's city list*\n\n" +
                    "### Path Variables\n\n" +
                    "- *id*: User ID\n" +
                    "- *city*: City name\n\n" +
                    "### Sample Output\n\n ```\n" + "{\n" +
                    "  \"data\": {\n" +
                    "    \"id\": 1,\n" +
                    "    \"username\": \"farukkavlak\",\n" +
                    "    \"cities\": [\n" +
                    "      \"Ankara\",\n" +
                    "      \"Istanbul\"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"responseDate\": \"2022-07-08T00:24:10.701+00:00\",\n" +
                    "  \"messages\": null,\n" +
                    "  \"success\": true\n" +
                    "}" + "\n```\n\n",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserCitiesSaveRequestDto.class),
                    examples = @ExampleObject(value = "{\n" +
                            "  \"city\": \"Ankara\"\n" +
                            "}")))
            )
    @DeleteMapping("/{id}/cities/{city}")
    public ResponseEntity delete(@PathVariable("id") Long id,@PathVariable("city") String city, @RequestHeader(required = false, value = "Authorization") String token) {
        jwtService.checkIdAndTokenMatch(id, token.substring(7));
        return ResponseEntity.ok(RestResponse.of(userService.deleteCity(id,city)));
    }

    @Operation( tags = "User-Controller",
            summary = "Get User Cities",
            description = "### Requirements\n" +
                    "- *User should be logged in*\n " +
                    "- *User ID in path variable should be same with logged in user id*\n\n" +
                    "### Path Variables\n\n" +
                    "- *id*: User ID\n\n" +
                    "### Sample Output\n\n ```\n" + "{\n" +
                    "  \"data\": {\n" +
                    "    \"Ankara\"" +",\n" +
                    "    \"Istanbul\"" + "\n" +
                    "  },\n" +
                    "  \"responseDate\": \"2022-07-08T00:24:10.701+00:00\",\n" +
                    "  \"messages\": null,\n" +
                    "  \"success\": true\n" +
                    "}" + "\n```\n\n")
    @GetMapping("/{id}/cities")
    public ResponseEntity getCities(@PathVariable Long id, @RequestHeader(required = false, value = "Authorization") String token) {
        jwtService.checkIdAndTokenMatch(id, token.substring(7));
        return ResponseEntity.ok(RestResponse.of(userService.getCities(id)));
    }

}
