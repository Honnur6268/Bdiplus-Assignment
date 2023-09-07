package com.bdiplus.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class BdiplusTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(BdiplusTaskApplication.class, args);
	}

}
