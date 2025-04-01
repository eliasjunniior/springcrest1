package com.example.Atividadecrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/info")
public class InfoController {

    private static final Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    private RestTemplate restTemplate;

    private final String externalApiUrl = "https://dog.ceo/api/breeds/image/random";

    @GetMapping
    public ResponseEntity<Map<String, String>> getExternalInfo() {
        try {
            logger.info("Chamando a API externa: {}", externalApiUrl);
            DogApiResponse response = restTemplate.getForObject(externalApiUrl, DogApiResponse.class);

            if (response != null && "success".equals(response.getStatus())) {
                Map<String, String> result = new HashMap<>();
                result.put("message", response.getMessage()); // O front espera um campo chamado "message"
                return ResponseEntity.ok(result);
            } else {
                logger.warn("Resposta da API inválida: {}", response);
                return ResponseEntity.badRequest().body(Map.of("error", "Erro ao acessar a API externa"));
            }
        } catch (Exception e) {
            logger.error("Erro ao acessar a API externa", e);
            return ResponseEntity.status(500).body(Map.of("error", "Erro ao acessar a API: " + e.getMessage()));
        }
    }

    // Classe auxiliar para mapear a resposta da API de cachorros
    private static class DogApiResponse {
        private String status;
        private String message;

        public String getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }
}
