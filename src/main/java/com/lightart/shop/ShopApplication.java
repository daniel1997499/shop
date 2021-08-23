package com.lightart.shop;

import com.lightart.shop.model.OrderItem;
import com.lightart.shop.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

	@Lazy
	@Bean
	public CommandLineRunner before(OrderRepository orderRepo) {
		return (args) -> {
			//Creating placeholder
			orderRepo.save(new OrderItem("name1","address1 at 1 an 3","065465406","mail1@gmail.com","item001",5L));
			orderRepo.save(new OrderItem("name2","address2","06475669413135","mail2@gmail.com","item005",15L));
			orderRepo.save(new OrderItem("name3","address3","155456464531234","mail3@gmail.com","item003",55L));
			orderRepo.save(new OrderItem("name4","address4","546464563643","mail4@gmail.com","item004",55L));
		};
	}
}
