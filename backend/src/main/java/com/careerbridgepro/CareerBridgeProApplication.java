package com.careerbridgepro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CareerBridgeProApplication {

    public static void main(String[] args) {
        SpringApplication.run(CareerBridgeProApplication.class, args);
    }
}
