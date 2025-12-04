package com.example.desconto;

import com.example.model.Pedido;

import java.math.BigDecimal;

public class SemDesconto implements EstrategiaDesconto {

    @Override
    public BigDecimal aplicarDesconto(Pedido pedido, BigDecimal valorBase) {
        return valorBase;
    }
}
