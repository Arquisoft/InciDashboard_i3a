package com.uniovi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableJpaRepositories("com.uniovi.repository")
@EnableMongoRepositories("com.uniovi.repository")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}