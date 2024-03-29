package com.boarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class BoardingCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardingCoreApplication.class, args);
    }
}
