package com.example.jodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.jodel")
@EnableJpaRepositories(basePackages = "com.example.jodel")
public class JodelApplication {

	public static void main(String[] args) {
		SpringApplication.run(JodelApplication.class, args);
	}

}
