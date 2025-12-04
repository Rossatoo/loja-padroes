package com.example.factory;

import com.example.model.Produto;
import com.example.model.ProdutoFisico;

import java.math.BigDecimal;

public class CadastroProdutoFisico extends CadastroProduto {

    @Override
    protected Produto criarProduto(String nome, BigDecimal preco){
        return new ProdutoFisico(nome, preco);
    }
}
