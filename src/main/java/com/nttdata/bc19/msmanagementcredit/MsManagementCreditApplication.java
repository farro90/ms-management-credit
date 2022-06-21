package com.nttdata.bc19.msmanagementcredit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsManagementCreditApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsManagementCreditApplication.class, args);
	}

}
