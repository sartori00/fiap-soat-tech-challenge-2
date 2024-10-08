package br.com.fiap.tech_challenge.infra.config.bean;

import br.com.fiap.tech_challenge.application.persistence.OrderPersistence;
import br.com.fiap.tech_challenge.application.usecase.order.impl.EvolveOrderUseCaseImpl;
import br.com.fiap.tech_challenge.application.usecase.order.impl.evolve.EvolveToFinished;
import br.com.fiap.tech_challenge.application.usecase.order.impl.evolve.EvolveToReady;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EvolveOrderUseCaseConfig {

	@Bean
	public EvolveOrderUseCaseImpl evolveOrderUseCase(OrderPersistence orderPersistence,
			EvolveToFinished evolveToFinished, EvolveToReady evolveToReady) {
		return new EvolveOrderUseCaseImpl(orderPersistence, List.of(evolveToFinished, evolveToReady));
	}

}
