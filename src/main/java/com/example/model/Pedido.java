package com.example.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Pedido {

    private String cliente;
    private boolean clienteVip;
    private List<ItemPedido> itens = new ArrayList<>();
    private BigDecimal total = BigDecimal.ZERO;

    public Pedido(String cliente, boolean clienteVip){
        this.cliente = cliente;
        this.clienteVip = clienteVip;
    }

    public boolean isClienteVip(){
        return clienteVip;
    }

    public void adicionarItem(ItemPedido item){
        itens.add(item);
    }
}
