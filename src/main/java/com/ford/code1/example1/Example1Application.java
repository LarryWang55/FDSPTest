package com.ford.code1.example1;

import com.ford.cloudnative.annotations.EnableFordSecurityTools;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFordSecurityTools
public class Example1Application {

	public static void main(String[] args) {
		SpringApplication.run(Example1Application.class, args);
	}

}
