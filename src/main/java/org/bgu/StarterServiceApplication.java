package org.bgu;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
public class StarterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarterServiceApplication.class, args);
	}

}
