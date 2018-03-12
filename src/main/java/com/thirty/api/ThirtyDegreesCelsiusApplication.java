package com.thirty.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@EnableAutoConfiguration
@SpringBootApplication
@EnableCaching
public class ThirtyDegreesCelsiusApplication extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ThirtyDegreesCelsiusApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ThirtyDegreesCelsiusApplication.class, args);
	}
}
