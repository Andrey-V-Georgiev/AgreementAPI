package com.agreement_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgreementAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgreementAPIApplication.class, args);
        System.out.println("Application is running on port: 8080");
    }

}
