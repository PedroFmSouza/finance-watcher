package com.pedro.financewatcher.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Diz: "Eu sou um garçom que responde requisições REST (JSON)"
@RequestMapping("/despesas") // Diz: "Eu atendo no endereço /despesas"
public class DespesaController {
    @GetMapping("/hello") // Diz: "Quando alguém pedir um GET em /despesas/hello..."
    public String dizerOla() {
        return "Olá! O Back-end está funcionando!";
    }
}
