package com.fiktac.SmartPlugApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartPlugAppApplication {

	public static void main(String[] args) {
		System.out.println("I'm alive!");
		SpringApplication.run(SmartPlugAppApplication.class, args);
	}
}
