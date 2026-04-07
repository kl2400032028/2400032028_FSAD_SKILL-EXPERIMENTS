package com.skill;

import com.skill.model.Product;
import com.skill.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Skill8Application {

	public static void main(String[] args) {
		SpringApplication.run(Skill8Application.class, args);
	}

    @Bean
    public CommandLineRunner initData(ProductRepository repository) {
        return args -> {
            repository.save(new Product("Laptop", "Electronics", 1200.0));
            repository.save(new Product("Smartphone", "Electronics", 800.0));
            repository.save(new Product("Headphones", "Electronics", 150.0));
            repository.save(new Product("Coffee Maker", "Home", 80.0));
            repository.save(new Product("Desk Chair", "Furniture", 250.0));
            repository.save(new Product("Backpack", "Accessories", 45.0));
            System.out.println("Sample products inserted.");
        };
    }
}
