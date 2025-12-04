package com.example.factory;

import com.example.model.Produto;
import com.example.model.ProdutoDigital;

import java.math.BigDecimal;

public class CadastroProdutoDigital extends CadastroProduto{

    @Override
    protected Produto criarProduto(String nome, BigDecimal preco){
        return new ProdutoDigital(nome, preco);
    }
}
