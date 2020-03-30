package com.github.douglsantos.usecase;

import com.github.douglsantos.usecase.domain.request.PedidoRequestDomain;
import com.github.douglsantos.usecase.domain.response.PedidoResponseDomain;
import com.github.douglsantos.usecase.gateway.PedidoGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(PedidoUseCase.class);

    @Autowired
    private PedidoGateway pedidoGateway;

    public List<PedidoResponseDomain> findAll() {
        return this.pedidoGateway.findAll();
    }

    public void saveAll(final List<PedidoResponseDomain> pedidosResponseDomain) {
        List<PedidoRequestDomain> pedidosRequestDomain = new ArrayList<>();

        for(PedidoResponseDomain pedidoResponse : pedidosResponseDomain) {
            PedidoRequestDomain pedidoRequestDomain = new PedidoRequestDomain();
            pedidoRequestDomain.setUuid(pedidoResponse.getUuid());
            pedidoRequestDomain.setNomePedido(pedidoResponse.getNomeProduto());
            pedidoRequestDomain.setValorUnitario(ICMSUseCase.calcular(pedidoResponse.getValorUnitario()));

            LOGGER.info("Pedido Nº: {} com valor final de: {}", pedidoRequestDomain.getUuid(), pedidoRequestDomain.getValorUnitario().setScale(2, RoundingMode.UP));
            pedidosRequestDomain.add(pedidoRequestDomain);
        }

        this.pedidoGateway.saveAll(pedidosRequestDomain);
    }
}
