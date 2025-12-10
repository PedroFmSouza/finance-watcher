package com.pedro.financewatcher.controller;

import com.pedro.financewatcher.model.Despesa;
import com.pedro.financewatcher.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Diz: "Eu sou um garçom que responde requisições REST (JSON)"
@RequestMapping("/despesas") // Diz: "Eu atendo no endereço /despesas"
public class DespesaController {

    //O Spring automaticamente joga nosso repositório aqui
    @Autowired
    private DespesaRepository repository;

    // 1. MÉTODO PARA SALVAR (O Front manda dados -> Back salva)
    // @PostMapping: Indica que aceita requisições do tipo POST (usado para criar coisas)
    // @RequestBody: O JSON que vier da internet será convertido automaticamente num objeto 'Despesa'
    @PostMapping
    public Despesa salvar(@RequestBody Despesa despesa) {
        // O repository faz o insert no banco e retorna o objeto salvo (já com o ID gerado)
        return repository.save(despesa);
    }

    // 2. MÉTODO PARA LISTAR (O Front pede dados <- Back entrega)
    // @GetMapping: Indica que aceita requisições do tipo GET (usado para ler coisas)
    @GetMapping
    public List<Despesa> listarTodas() {
        // O repository vai no banco, faz um "SELECT * FROM despesa" e retorna uma lista
        return repository.findAll();
    }
}
