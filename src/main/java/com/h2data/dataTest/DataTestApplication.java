package com.h2data.dataTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@AutoConfiguration
public class DataTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataTestApplication.class, args);
	}

}
