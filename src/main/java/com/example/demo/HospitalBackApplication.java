package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com/example/demo/controller",
		"com/example/demo/model",
		"com/example/demo/repository",
		"com/example/demo/service"
})
public class HospitalBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalBackApplication.class, args);
	}

}
