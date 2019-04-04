package com.xapp.moneyassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.xapp.moneyassistant")
@EntityScan(basePackages = "com.xapp.moneyassistant.model")
@EnableJpaRepositories(basePackages = "com.xapp.moneyassistant.repository")
public class MoneyAssistantApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyAssistantApplication.class, args);
	}

}
