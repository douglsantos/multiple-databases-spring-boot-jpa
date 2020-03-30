package com.github.douglsantos.usecase.domain.request;

import java.math.BigDecimal;
import java.util.UUID;

public class PedidoRequestDomain {

    private UUID uuid;
    private String nomePedido;
    private BigDecimal valorUnitario;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNomePedido() {
        return nomePedido;
    }

    public void setNomePedido(String nomePedido) {
        this.nomePedido = nomePedido;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
