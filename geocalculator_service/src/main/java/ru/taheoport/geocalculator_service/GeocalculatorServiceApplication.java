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
//		PotenotCalculator potenotCalculator = new PotenotCalculatorImpl();
//		double rezult = potenotCalculator.targetY(200000.0, 137114.0, 100000.0, 4.384873098);
//		System.out.println(Math.round(rezult));

		System.out.println("Hello, World");


	}

}
