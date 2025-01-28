package com.a2.swifting_fitness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SwiftingFitnessApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwiftingFitnessApplication.class, args);
	}

}
