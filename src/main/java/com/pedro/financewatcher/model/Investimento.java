package com.pedro.financewatcher.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
public class Investimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String descricao;
    private Double valorInicial;
    private Double taxaMensal;
    private LocalDate dataCompra;

    @Transient
    private double valorAtual;
    @Transient
    private double rendimento;

    public void calcularJuros() {
        if (dataCompra != null && valorInicial != null && taxaMensal != null) {
            long diasPassados = ChronoUnit.DAYS.between(dataCompra, LocalDate.now());

            // Fórmula: M = C * (1 + i)^t
            // Convertendo taxa mensal para diária aproximada para ver o dinheiro crescer todo dia
            double taxaDiaria = taxaMensal / 30 / 100;

            this.valorAtual = valorInicial * Math.pow((1 + taxaDiaria), diasPassados);
            this.rendimento = this.valorAtual - this.valorInicial;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public double getTaxaMensal() {
        return taxaMensal;
    }

    public void setTaxaMensal(double taxaMensal) {
        this.taxaMensal = taxaMensal;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }
}
