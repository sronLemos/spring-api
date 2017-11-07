package com.example.spring.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.spring.api.config.property.SpringApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(SpringApiProperty.class)
public class SpirngApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpirngApiApplication.class, args);
	}
}
