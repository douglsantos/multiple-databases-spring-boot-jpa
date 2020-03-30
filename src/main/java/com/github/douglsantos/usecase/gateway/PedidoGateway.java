package com.github.douglsantos.usecase.gateway;

import com.github.douglsantos.usecase.domain.request.PedidoRequestDomain;
import com.github.douglsantos.usecase.domain.response.PedidoResponseDomain;

import java.util.List;

public interface PedidoGateway {
    List<PedidoResponseDomain> findAll();
    void saveAll(List<PedidoRequestDomain> pedidosDomain);
}
