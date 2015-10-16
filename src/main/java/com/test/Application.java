package com.test;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    static Logger log = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {

        log.info("Producer Application Start");
        SpringApplication.run(Application.class, args);
    }
}
