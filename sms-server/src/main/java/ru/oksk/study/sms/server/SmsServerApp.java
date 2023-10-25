package ru.oksk.study.sms.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "ru.oksk.study")
@EnableMongoRepositories("ru.oksk.study.common.repository")
@EnableFeignClients
@EnableDiscoveryClient
public class SmsServerApp {

	public static void main(String[] args) {
		SpringApplication.run(SmsServerApp.class, args);
	}
}
