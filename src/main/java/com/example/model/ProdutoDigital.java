package com.example.model;

import java.math.BigDecimal;

public class ProdutoDigital extends Produto{

    public ProdutoDigital(String nome, BigDecimal preco){
        super(nome, preco);
    }

    @Override
    public String getTipo(){
        return "DIGITAL";
    }
}
