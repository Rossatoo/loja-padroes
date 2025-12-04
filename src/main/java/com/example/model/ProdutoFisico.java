package com.example.model;

import java.math.BigDecimal;

public class ProdutoFisico extends Produto{

    public ProdutoFisico(String nome, BigDecimal preco) {
        super(nome, preco);
    }

    @Override
    public String getTipo(){
        return "FISICO";
    }
}
