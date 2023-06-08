package com.farukkavlak.akbankbootcamp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.farukkavlak.akbankbootcamp.client")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@OpenAPIDefinition(info = @Info(title = "Akbank Bootcamp", version = "1.0", description = "Akbank Bootcamp API Documentation v1.0"))
public class AkbankBootcampApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkbankBootcampApplication.class, args);
	}

}
