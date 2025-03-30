package com.example.Atividadecrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/info")
public class InfoController {

private static final Logger logger = LoggerFactory.getLogger(InfoController.class);

@Autowired
private RestTemplate restTemplate;

private final String externalApiUrl = "https://dog.ceo/api/breeds/image/random";

@GetMapping
public String getExternalInfo() {
try {
logger.info("Chamando a API externa: {}", externalApiUrl);
DogApiResponse response = restTemplate.getForObject(externalApiUrl, DogApiResponse.class);
logger.info("Resposta da API: {}", response);
if (response != null && "success".equals(response.getStatus())) {
return "URL da imagem do cachorro: " + response.getMessage();
} else {
logger.warn("Resposta da API inválida: {}", response);
return "Erro ao acessar a API externa ou resposta inválida.";
}
} catch (Exception e) {
logger.error("Erro ao acessar a API externa", e);
return "Erro ao acessar a API externa: " + e.getMessage();
}
}
}