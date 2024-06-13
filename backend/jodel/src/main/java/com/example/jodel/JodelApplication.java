package com.example.jodel;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jodel.authentification.NameConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EntityScan(basePackages = "com.example.jodel")
@EnableJpaRepositories(basePackages = "com.example.jodel")
@RequestMapping("/jodel/api")
@RestController
public class JodelApplication {

	public static void main(String[] args) {
		SpringApplication.run(JodelApplication.class, args);

	}

	@GetMapping("/me")
	public String getCurrentUser(
			@RequestHeader("Authorization") String authorizationHeader) {

		return new NameConverter(authorizationHeader).sub;

	}

}
