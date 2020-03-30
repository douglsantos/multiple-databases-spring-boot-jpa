package com.github.douglsantos.dataprovider.mapper;

import com.github.douglsantos.dataprovider.model.request.PedidoRequestModelEntity;
import com.github.douglsantos.dataprovider.model.response.PedidoResponseModelEntity;
import com.github.douglsantos.usecase.domain.request.PedidoRequestDomain;
import com.github.douglsantos.usecase.domain.response.PedidoResponseDomain;

import java.util.ArrayList;
import java.util.List;

public class PedidoModelMapper {

    private PedidoModelMapper() {}

    public static List<PedidoResponseDomain> from(final List<PedidoResponseModelEntity> pedidosModelEntity) {
        List<PedidoResponseDomain> pedidosResponse = new ArrayList<>();

        for(PedidoResponseModelEntity pedidoModelEntity : pedidosModelEntity) {
            PedidoResponseDomain pedidoResponseDomain = new PedidoResponseDomain();

            pedidoResponseDomain.setUuid(pedidoModelEntity.getUuid());
            pedidoResponseDomain.setNomeProduto(pedidoModelEntity.getNomeProduto());
            pedidoResponseDomain.setValorUnitario(pedidoModelEntity.getValorUnitario());

            pedidosResponse.add(pedidoResponseDomain);
        }

        return pedidosResponse;
    }

    public static List<PedidoRequestModelEntity> to(final List<PedidoRequestDomain> pedidosRequestDomain) {
        List<PedidoRequestModelEntity> pedidosRequest = new ArrayList<>();

        for(PedidoRequestDomain pedidoRequestDomain : pedidosRequestDomain) {
            PedidoRequestModelEntity pedidoRequestModelEntity = new PedidoRequestModelEntity();

            pedidoRequestModelEntity.setUuid(pedidoRequestDomain.getUuid());
            pedidoRequestModelEntity.setNomePedido(pedidoRequestDomain.getNomePedido());
            pedidoRequestModelEntity.setValorUnitario(pedidoRequestDomain.getValorUnitario());

            pedidosRequest.add(pedidoRequestModelEntity);
        }

        return pedidosRequest;
    }
}
