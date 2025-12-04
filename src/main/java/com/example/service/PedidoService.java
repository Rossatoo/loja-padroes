package com.example.service;

import com.example.desconto.EstrategiaDesconto;
import com.example.desconto.SemDesconto;
import com.example.model.ItemPedido;
import com.example.model.Pedido;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PedidoService {

    private EstrategiaDesconto estrategiaDesconto = new SemDesconto();

    private final List<Pedido> pedidos = new ArrayList<>();

    public void calcularTotal(Pedido pedido) {
        BigDecimal total = BigDecimal.ZERO;

        for (ItemPedido item : pedido.getItens()){
            total = total.add(item.getSubtotal());
        }

        BigDecimal totalComDesconto = estrategiaDesconto.aplicarDesconto(pedido, total);
        pedido.setTotal(totalComDesconto);
    }

    public void setEstrategiaDesconto(EstrategiaDesconto estrategiaDesconto) {
        this.estrategiaDesconto = estrategiaDesconto;
    }

    public void registrar(Pedido pedido){
        pedidos.add(pedido);
    }

    public List<Pedido> listarTodos(){
        return Collections.unmodifiableList(pedidos);
    }
}
