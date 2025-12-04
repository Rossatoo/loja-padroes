package com.example.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemPedido {

    private Produto produto;
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public BigDecimal getSubtotal(){
        return produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }
}
