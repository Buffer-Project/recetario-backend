package com.buffer.recetariobackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class RecetarioBackendApplication {

    @Autowired
    IRecetaRepository IRecetaRepository;

    public static void main(String[] args) {
        SpringApplication.run(RecetarioBackendApplication.class, args);
    }

}
