package com.sree.banking.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sreenivasulu.Avula
 *
 */

@EnableJpaRepositories
@SpringBootApplication
@EnableWebSecurity
public class TransactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
