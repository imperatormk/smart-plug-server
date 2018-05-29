package com.fiktac.SmartPlugApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class })
public class SmartPlugAppApplication {

	public static void main(String[] args) {
		System.out.println("I'm alive!");
		SpringApplication.run(SmartPlugAppApplication.class, args);
	}
}
