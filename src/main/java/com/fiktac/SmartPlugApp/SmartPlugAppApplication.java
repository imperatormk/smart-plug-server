package com.fiktac.SmartPlugApp;

import com.fiktac.SmartPlugApp.device.DeviceController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = DeviceController.class)
public class SmartPlugAppApplication {

	public static void main(String[] args) {
		System.out.println("I'm alive!");
		SpringApplication.run(SmartPlugAppApplication.class, args);
	}
}
