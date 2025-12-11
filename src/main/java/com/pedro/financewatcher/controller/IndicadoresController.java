package com.pedro.financewatcher.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@RestController
@RequestMapping("/indicadores")
public class IndicadoresController {

    @GetMapping
    public Map<String, Double> getIndicadores() {
        String urlBcb = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.432/dados/ultimos/1?formato=json";

        RestTemplate restTemplate = new RestTemplate();
        Map[] resposta = restTemplate.getForObject(urlBcb, Map[].class);

        if (resposta != null && resposta.length > 0) {
            String valorString = (String) resposta[0].get("valor");
            Double selicAnual = Double.parseDouble(valorString);

            // Regras de Mercado:
            // O CDI geralmente é 0.10 a menos que a Selic
            Double cdiAnual = selicAnual - 0.10;

            Double poupancaAnual = (selicAnual > 8.5) ? 6.17 : (selicAnual * 0.70);

            return Map.of(
                    "selic", selicAnual,
                    "cdi", cdiAnual,
                    "poupanca", poupancaAnual
            );
        }

        // Fallback caso o BC esteja fora do ar
        return Map.of("selic", 11.25, "cdi", 11.15, "poupanca", 6.17);
    }
}