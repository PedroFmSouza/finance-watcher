package com.pedro.financewatcher.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/cambio")
public class CambioController {

    @GetMapping
    public Object getCotacoes() {
        // Adicionei: CHF (Suíça), MXN (México) e ZAR (África do Sul)
        String urlExterna = "https://economia.awesomeapi.com.br/last/USD-BRL,EUR-BRL,GBP-BRL,JPY-BRL,AUD-BRL,CAD-BRL,CNY-BRL,ARS-BRL,BTC-BRL,CHF-BRL,MXN-BRL,ZAR-BRL";

        RestTemplate navegador = new RestTemplate();
        return navegador.getForObject(urlExterna, Object.class);
    }
}