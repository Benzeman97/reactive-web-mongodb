package com.benz.reactive.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveWebMongodbApplication {

    public static void main(String[] args) {

        SpringApplication.run(ReactiveWebMongodbApplication.class, args);
    }

}
