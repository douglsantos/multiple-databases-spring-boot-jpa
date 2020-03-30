package com.github.douglsantos;

import com.github.douglsantos.dataprovider.model.response.PedidoResponseModelEntity;
import com.github.douglsantos.dataprovider.repository.response.PedidoMSSQLRepository;
import com.github.douglsantos.usecase.PedidoUseCase;
import com.github.douglsantos.usecase.domain.response.PedidoResponseDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EntityScan({
		"com.github.douglsantos.datapriver.model",
})
@SpringBootApplication
public class MultipleDatabasesSpringBootJpaApplication implements CommandLineRunner {


	@Autowired
	private PedidoMSSQLRepository pedidoMSSQLRepository;
	@Autowired
	private PedidoUseCase pedidoUseCase;

	public static void main(String[] args) {
		SpringApplication.run(MultipleDatabasesSpringBootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		List<PedidoResponseModelEntity> pedidosCargaInicial = new ArrayList<>();

		PedidoResponseModelEntity pedidoResponseOne = new PedidoResponseModelEntity();
		pedidoResponseOne.setUuid(UUID.randomUUID());
		pedidoResponseOne.setNomeProduto("Xbox One X");
		pedidoResponseOne.setValorUnitario(new BigDecimal("2999.99"));

		PedidoResponseModelEntity pedidoResponseTwo = new PedidoResponseModelEntity();
		pedidoResponseTwo.setUuid(UUID.randomUUID());
		pedidoResponseTwo.setNomeProduto("PlayStation 5");
		pedidoResponseTwo.setValorUnitario(new BigDecimal("3599.99"));

		PedidoResponseModelEntity pedidoResponseThree = new PedidoResponseModelEntity();
		pedidoResponseThree.setUuid(UUID.randomUUID());
		pedidoResponseThree.setNomeProduto("Kindle 10ª Gen");
		pedidoResponseThree.setValorUnitario(new BigDecimal("345.99"));

		pedidosCargaInicial.add(pedidoResponseOne);
		pedidosCargaInicial.add(pedidoResponseTwo);
		pedidosCargaInicial.add(pedidoResponseThree);

		this.pedidoMSSQLRepository.saveAll(pedidosCargaInicial);

		// INICIANDO PROCESSO DE CONSUMIR DOIS DATABASES

		Thread.sleep(6000);


		List<PedidoResponseDomain> pedidosResponseDomain = this.pedidoUseCase.findAll();

		for(PedidoResponseDomain pedidoResponseDomain : pedidosResponseDomain) {
			System.out.println(pedidoResponseDomain.getUuid());
		}

		this.pedidoUseCase.saveAll(pedidosResponseDomain);

	}
}
