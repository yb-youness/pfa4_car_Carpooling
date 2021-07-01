package com.pfa.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BackendApplication {


	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		  return new BCryptPasswordEncoder();
	}
     

	
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
