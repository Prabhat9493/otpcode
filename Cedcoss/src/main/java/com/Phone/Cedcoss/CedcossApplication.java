package com.Phone.Cedcoss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories
public class CedcossApplication {

	public static void main(String[] args) {
		SpringApplication.run(CedcossApplication.class, args);
	}

}
