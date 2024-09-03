package com.asecurityguru.ollamarestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.asecurityguru.ollamarestapi.functions.JiraApiProperties;

@SpringBootApplication
@ComponentScan(basePackages = "com.asecurityguru.ollamarestapi")
@EnableConfigurationProperties(JiraApiProperties.class)
public class OllamarestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OllamarestapiApplication.class, args);
	}

}
