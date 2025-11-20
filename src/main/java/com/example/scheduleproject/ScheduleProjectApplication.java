package com.example.scheduleproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScheduleProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleProjectApplication.class, args);
    }

}
