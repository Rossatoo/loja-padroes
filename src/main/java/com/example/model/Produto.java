package com.example.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public abstract class Produto {
    private String nome;
    private BigDecimal preco;

    protected Produto(String nome, BigDecimal preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public abstract String getTipo();

}
