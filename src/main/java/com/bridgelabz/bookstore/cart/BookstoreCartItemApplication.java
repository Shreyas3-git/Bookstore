package com.bridgelabz.bookstore.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookstoreCartItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreCartItemApplication.class, args);
	}

}
