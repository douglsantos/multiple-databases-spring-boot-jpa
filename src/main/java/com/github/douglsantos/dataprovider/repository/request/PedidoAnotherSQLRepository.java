package com.github.douglsantos.dataprovider.repository.request;

import com.github.douglsantos.dataprovider.model.request.PedidoRequestModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoAnotherSQLRepository extends JpaRepository<PedidoRequestModelEntity, UUID> { }
