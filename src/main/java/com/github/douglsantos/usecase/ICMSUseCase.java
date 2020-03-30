package com.github.douglsantos.usecase;

import java.math.BigDecimal;

public class ICMSUseCase {

    private ICMSUseCase() {}

    public static BigDecimal calcular(BigDecimal valorUnitarioProduto) {
        return valorUnitarioProduto.add(valorUnitarioProduto.multiply(new BigDecimal("0.13")).divide(new BigDecimal("100")));
    }
}
