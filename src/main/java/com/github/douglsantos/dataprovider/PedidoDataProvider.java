package com.github.douglsantos.dataprovider;

import com.github.douglsantos.dataprovider.mapper.PedidoModelMapper;
import com.github.douglsantos.dataprovider.model.request.PedidoRequestModelEntity;
import com.github.douglsantos.dataprovider.model.response.PedidoResponseModelEntity;
import com.github.douglsantos.dataprovider.repository.request.PedidoAnotherSQLRepository;
import com.github.douglsantos.dataprovider.repository.response.PedidoMSSQLRepository;
import com.github.douglsantos.usecase.domain.request.PedidoRequestDomain;
import com.github.douglsantos.usecase.domain.response.PedidoResponseDomain;
import com.github.douglsantos.usecase.gateway.PedidoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoDataProvider implements PedidoGateway {

    private PedidoMSSQLRepository pedidoMSSQLRepository;
    private PedidoAnotherSQLRepository pedidoAnotherSQLRepository;

    @Autowired
    public PedidoDataProvider(PedidoMSSQLRepository pedidoMSSQLRepository, PedidoAnotherSQLRepository pedidoAnotherSQLRepository) {
        this.pedidoMSSQLRepository = pedidoMSSQLRepository;
        this.pedidoAnotherSQLRepository = pedidoAnotherSQLRepository;
    }

    @Override
    public List<PedidoResponseDomain> findAll() {
        List<PedidoResponseModelEntity> pedidosResponseModelEntity = this.pedidoMSSQLRepository.findAll();

        List<PedidoResponseDomain> pedidosResponseDomain = PedidoModelMapper.from(pedidosResponseModelEntity);

        return pedidosResponseDomain;
    }

    @Override
    public void saveAll(List<PedidoRequestDomain> pedidosDomain) {
        List<PedidoRequestModelEntity> pedidosRequestModelEntitt = PedidoModelMapper.to(pedidosDomain);

        this.pedidoAnotherSQLRepository.saveAll(pedidosRequestModelEntitt);
    }
}
