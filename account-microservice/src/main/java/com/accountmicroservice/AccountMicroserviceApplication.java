package com.accountmicroservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;



@SpringBootApplication
public class AccountMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountMicroserviceApplication.class, args);
	}

}

