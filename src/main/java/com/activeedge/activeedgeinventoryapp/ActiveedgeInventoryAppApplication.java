package com.activeedge.activeedgeinventoryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ActiveedgeInventoryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActiveedgeInventoryAppApplication.class, args);
    }

}
