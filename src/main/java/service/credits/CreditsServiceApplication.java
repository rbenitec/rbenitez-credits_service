package service.credits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CreditsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditsServiceApplication.class, args);
	}

}
