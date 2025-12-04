package com.example.desconto;

import com.example.model.Pedido;

import java.math.BigDecimal;

public class DescontoClienteVip implements  EstrategiaDesconto {

    @Override
    public BigDecimal aplicarDesconto(Pedido pedido, BigDecimal valorBase) {
        if (pedido.isClienteVip()) {
            return valorBase.multiply(new BigDecimal("0.90"));
        }
        return valorBase;
    }
}
