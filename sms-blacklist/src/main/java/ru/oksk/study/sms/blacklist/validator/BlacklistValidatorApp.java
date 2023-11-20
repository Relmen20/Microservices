package ru.oksk.study.sms.blacklist.validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "ru.oksk.study")
@EnableMongoRepositories(basePackages = "ru.oksk.study")
@EnableFeignClients
@EnableDiscoveryClient

public class BlacklistValidatorApp {

	public static void main(String[] args) {
		SpringApplication.run(BlacklistValidatorApp.class, args);
	}

}
