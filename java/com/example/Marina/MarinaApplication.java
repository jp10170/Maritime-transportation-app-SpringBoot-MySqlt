package com.example.Marina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"com.example.Marina.Models", "Auth", "Controller", "Repository", "Service"})
@EnableJpaRepositories("Repository")
public class MarinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarinaApplication.class, args);
	}

}
