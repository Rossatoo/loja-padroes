package com.example.desconto;

import com.example.model.Pedido;

import java.math.BigDecimal;

public class DescontoValorAlto implements  EstrategiaDesconto{

    @Override
    public BigDecimal aplicarDesconto(Pedido pedido, BigDecimal valorBase) {
        if (valorBase.compareTo(new BigDecimal("200.00")) >= 0) {
            return valorBase.multiply(new BigDecimal("0.85"));
        }
        return valorBase;
    }
}
