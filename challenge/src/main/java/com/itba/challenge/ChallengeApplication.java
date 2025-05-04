package com.itba.challenge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ChallengeApplication {

    public static void main(String[] args) {
        log.info("Starting challenge application...");
        SpringApplication.run(ChallengeApplication.class, args);
        log.info("Challenge application started!");
    }

}
