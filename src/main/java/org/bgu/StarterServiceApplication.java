package org.bgu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StarterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarterServiceApplication.class, args);
	}

}
