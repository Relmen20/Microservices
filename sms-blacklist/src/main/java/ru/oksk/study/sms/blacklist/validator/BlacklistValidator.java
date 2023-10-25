package ru.oksk.study.sms.blacklist.validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "ru.oksk.study")
@EnableMongoRepositories(basePackages = "ru.oksk.study")
public class BlacklistValidator {

	public static void main(String[] args) {
		SpringApplication.run(BlacklistValidator.class, args);
	}

}
