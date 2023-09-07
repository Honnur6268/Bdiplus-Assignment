package com.bdiplus.task.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggreConfig {


	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();

	}

	private ApiInfo getInfo() {
		return new ApiInfo("BDI PLUS : Task", "Developed By Honnur Ali", "1.0", "Terms of Service",
				new Contact("Honnur Ali", "8073379047", "honnurcse.rymec@gmail.com"), "License", "API License",
				Collections.emptyList());
	}
}
