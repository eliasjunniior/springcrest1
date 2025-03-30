package com.example.Atividadecrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean; // Não se esqueça de importar esta anotação
import org.springframework.web.client.RestTemplate; // Não se esqueça de importar esta classe

@SpringBootApplication
public class AtividadecrestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtividadecrestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}