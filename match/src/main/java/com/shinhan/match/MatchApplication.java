package com.shinhan.match;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "repository", "domain", "service", "config", "com.shinhan.match"})
public class MatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatchApplication.class, args);
	}

}