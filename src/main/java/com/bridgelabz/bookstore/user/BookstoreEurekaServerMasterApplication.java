package com.bridgelabz.bookstore.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BookstoreEurekaServerMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreEurekaServerMasterApplication.class, args);
	}

}
