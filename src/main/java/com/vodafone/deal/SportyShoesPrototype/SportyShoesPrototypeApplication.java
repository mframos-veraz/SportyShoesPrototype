package com.vodafone.deal.SportyShoesPrototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.vodafone.deal.SportyShoesPrototype")
@EntityScan(basePackages = "com.vodafone.deal.SportyShoesPrototype.domain")
@EnableJpaRepositories(basePackages = "com.vodafone.deal.SportyShoesPrototype.repository")
public class SportyShoesPrototypeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportyShoesPrototypeApplication.class, args);
	}

}
