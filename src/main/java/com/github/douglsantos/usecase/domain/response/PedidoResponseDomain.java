package com.github.douglsantos.usecase.domain.response;

import java.math.BigDecimal;
import java.util.UUID;

public class PedidoResponseDomain {

    private UUID uuid;
    private String nomeProduto;
    private BigDecimal valorUnitario;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

}
