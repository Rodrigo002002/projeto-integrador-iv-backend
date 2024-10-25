package com.apiathletevision.apiathletevision;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
		"com.apiathletevision.apiathletevision.repositories"
})
@EntityScan(basePackages = {
		"com.apiathletevision.apiathletevision.entities"
})
public class ApiathletevisionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiathletevisionApplication.class, args);
	}

}
