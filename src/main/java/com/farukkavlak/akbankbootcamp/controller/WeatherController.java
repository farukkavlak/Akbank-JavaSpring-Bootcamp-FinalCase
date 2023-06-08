package com.farukkavlak.akbankbootcamp.controller;/*
Created by farukkavlak on 30.05.2023
@author: farukkavlak
@date: 30.05.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.dto.UserLoginRequestDto;
import com.farukkavlak.akbankbootcamp.generic.response.RestResponse;
import com.farukkavlak.akbankbootcamp.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/weathers")
@RequiredArgsConstructor
@CrossOrigin
public class WeatherController {
    private final WeatherService weatherService;
    @Operation( tags = "Weather-Controller",
            summary = "Get 5 days weather forecast from city name by using OpenWeatherMap API",
            description = "### Requirements\n" +
                    "- *Credentials should be valid*\n\n" +
                    "### Sample Request Parameters\n\n ```\n" +
                    "city: istanbul\n" +
                    "userId: 1 (It is optional, if it is null, it is anonymous user)\n" +
                    "\n```\n\n" +
                    "### Sample Output\n\n ```\n" + "{\n" +
                    "  \"data\": {\n" +
                    "    \"city\": \"Ankara\",\n" +
                    "    \"forecastResultList\": [\n" +
                    "      {\n" +
                    "        \"dateTimestamp\": 1625650800,\n" +
                    "        \"temperature\": 30.0,\n" +
                    "        \"weatherCondition\": \"Clouds\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"dateTimestamp\": 1625737200,\n" +
                    "        \"temperature\": 30.0,\n" +
                    "        \"weatherCondition\": \"Clouds\"\n" +
                    "      },\n" +
                    "      ...\n" +
                    "  },\n" +
                    "  \"responseDate\": \"2022-07-08T00:24:10.701+00:00\",\n" +
                    "  \"messages\": null,\n" +
                    "  \"success\": true\n" +
                    "}" + "\n```\n\n")
    @GetMapping()
    public ResponseEntity findByCity(@RequestParam String city, @RequestParam(required = false) Long userId) {
        return ResponseEntity.ok(RestResponse.of(weatherService.getWeather(userId,city)));
    }
}
