package com.github.douglsantos.dataprovider.repository.response;

import com.github.douglsantos.dataprovider.model.response.PedidoResponseModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoMSSQLRepository extends JpaRepository<PedidoResponseModelEntity, UUID> { }
