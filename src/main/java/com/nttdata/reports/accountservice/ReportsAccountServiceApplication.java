package com.nttdata.reports.accountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ReportsAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportsAccountServiceApplication.class, args);
	}

}
