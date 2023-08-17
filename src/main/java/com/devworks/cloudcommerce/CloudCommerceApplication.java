package com.devworks.cloudcommerce;

import com.devworks.cloudcommerce.module.user.model.User;
import com.devworks.cloudcommerce.shared.util.GenericBuilder;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudCommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudCommerceApplication.class, args);

		var test = GenericBuilder.of(User::new)
			.with(User::setPhoneNumber, "123")
			.build();

		System.out.println(test.getPhoneNumber());
	}

}
