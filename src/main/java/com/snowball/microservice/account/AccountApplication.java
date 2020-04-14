package com.snowball.microservice.account;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import com.snowball.microservice.account.controller.controller;

@SpringBootApplication
@EnableEurekaClient
public class AccountApplication {
	protected final Logger logger = LoggerFactory.getLogger(controller.class);
	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}
}
