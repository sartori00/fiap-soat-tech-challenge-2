package br.com.fiap.tech_challenge.application.usecase.order.impl;

import br.com.fiap.tech_challenge.application.persistence.OrderPersistence;
import br.com.fiap.tech_challenge.application.usecase.order.EvolveOrderUseCase;
import br.com.fiap.tech_challenge.application.usecase.order.impl.evolve.EvolveRules;

import java.util.List;
import java.util.UUID;

public class EvolveOrderUseCaseImpl implements EvolveOrderUseCase {

	private final OrderPersistence persistence;

	private final List<EvolveRules> evolveRules;

	public EvolveOrderUseCaseImpl(OrderPersistence persistence, List<EvolveRules> evolveRules) {
		this.persistence = persistence;
		this.evolveRules = evolveRules;
	}

	@Override
	public void evolveOrder(UUID id) {
		var order = persistence.findById(id).orElseThrow();

		evolveRules.forEach(rule -> rule.evolveTo(order));

		persistence.update(order);
	}

}
