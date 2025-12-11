package com.pedro.financewatcher.controller;

import com.pedro.financewatcher.model.Despesa;
import com.pedro.financewatcher.model.Investimento;
import com.pedro.financewatcher.repository.DespesaRepository;
import com.pedro.financewatcher.repository.InvestimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investimentos")
public class InvestimentoController {

    @Autowired
    private InvestimentoRepository investimentoRepository;

    @Autowired
    private DespesaRepository despesaRepository;

    @GetMapping
    public List<Investimento> listar() {
        List<Investimento> lista = investimentoRepository.findAll();
        lista.forEach(Investimento::calcularJuros);
        return lista;
    }

    @PostMapping
    public Investimento investir(@RequestBody Investimento novo) {
        // 1. Cria o Ativo
        Investimento investimentoSalvo = investimentoRepository.save(novo);

        // 2. Tira do Saldo (Cria Despesa)
        Despesa saida = new Despesa();
        saida.setDescricao("Aplicação: " + novo.getDescricao());
        saida.setValor(novo.getValorInicial());
        saida.setData(novo.getDataCompra());
        saida.setTipo("DESPESA");
        saida.setCategoria("Investimento");
        despesaRepository.save(saida);

        return investimentoSalvo;
    }

    // --- NOVO: Lógica de Resgate ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> resgatar(@PathVariable Long id) {
        Investimento inv = investimentoRepository.findById(id).orElse(null);

        if (inv != null) {
            // 1. Calcula quanto valeu o dinheiro até hoje
            inv.calcularJuros();

            // 2. Devolve para o Saldo (Cria Receita)
            Despesa entrada = new Despesa();
            entrada.setDescricao("Resgate: " + inv.getDescricao());
            entrada.setValor(inv.getValorAtual()); // Valor com Juros!
            entrada.setData(java.time.LocalDate.now()); // Data de Hoje
            entrada.setTipo("RECEITA");
            entrada.setCategoria("Rendimento");
            despesaRepository.save(entrada);

            // 3. Apaga o investimento da carteira
            investimentoRepository.delete(inv);
        }

        return ResponseEntity.ok().build();
    }
}