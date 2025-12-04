package com.example.factory;

import com.example.model.Produto;

import java.math.BigDecimal;

public abstract class CadastroProduto {

    public Produto registrarProduto(String nome, BigDecimal preco){
        Produto produto = criarProduto(nome, preco);
        return produto;
    }

    protected abstract Produto criarProduto(String nome, BigDecimal preco);
}
