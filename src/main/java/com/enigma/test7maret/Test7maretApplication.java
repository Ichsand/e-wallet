package com.enigma.test7maret;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.enigma.test7maret.Repository.AccountRepository;

@SpringBootApplication
public class Test7maretApplication {

	public static void main(String[] args) {
		SpringApplication.run(Test7maretApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AccountRepository repo) {
		return (args) -> {
			
		};
	}
}
