package com.example.desconto;

import com.example.model.Pedido;

import java.math.BigDecimal;

public interface EstrategiaDesconto {

    BigDecimal aplicarDesconto(Pedido pedido, BigDecimal valorBase);
}
