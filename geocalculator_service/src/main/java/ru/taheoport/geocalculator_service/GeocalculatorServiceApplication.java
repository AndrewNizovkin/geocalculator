package ru.taheoport.geocalculator_service;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.taheoport.geocalculator_service.mapper.PotenotCalculator;
import ru.taheoport.geocalculator_service.mapper.PotenotCalculatorImpl;

@SpringBootApplication
public class GeocalculatorServiceApplication {

	public static void main(String[] args) {
				SpringApplication.run(GeocalculatorServiceApplication.class, args);

	}

}
