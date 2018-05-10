package com.healthportal;


import com.healthportal.services.ShoppingCartService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.healthportal.repositories")
@ComponentScan(basePackages= {"com.healthportal.services","com.healthportal.controllers"})
@EntityScan("com.healthportal.entities")
public class HealthportalApplication {


    public static void main(String[] args) {
        SpringApplication.run(HealthportalApplication.class, args);
	}

	@Bean
    public ShoppingCartService createShoppingCartService(){
        return new ShoppingCartService();
    }


}
