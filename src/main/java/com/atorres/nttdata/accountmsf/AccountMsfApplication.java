package com.atorres.nttdata.accountmsf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@EnableDiscoveryClient
@EnableReactiveFeignClients
@SpringBootApplication
public class AccountMsfApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountMsfApplication.class, args);
	}

}
