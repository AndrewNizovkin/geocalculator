package ru.taheoport.geocalculator_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.taheoport.geocalculator_service.model.InverseTask;
import ru.taheoport.geocalculator_service.service.InverseTaskService;
import ru.taheoport.geocalculator_service.service.InverseTaskServiceServiceDefault;

@SpringBootApplication
public class GeocalculatorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeocalculatorServiceApplication.class, args);
		System.out.println("Hello, World");

	}

}
