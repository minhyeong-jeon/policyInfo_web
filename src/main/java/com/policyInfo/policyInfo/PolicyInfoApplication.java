package com.policyInfo.policyInfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PolicyInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolicyInfoApplication.class, args);
	}

}
