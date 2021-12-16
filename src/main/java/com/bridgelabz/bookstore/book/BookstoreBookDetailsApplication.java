package com.bridgelabz.bookstore.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookstoreBookDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreBookDetailsApplication.class, args);
	}

}
