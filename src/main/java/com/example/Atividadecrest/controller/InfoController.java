package com.example.Atividadecrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private RestTemplate restTemplate;

    private final String externalApiUrl = "https://dog.ceo/api/breeds/image/random"; // Exemplo: API de imagens de cachorros

    @GetMapping
    public String getExternalInfo() {
        try {
            String response = restTemplate.getForObject(externalApiUrl, String.class);
            return response;
        } catch (Exception e) {
            return "Erro ao acessar a API externa: " + e.getMessage();
        }
    }
}