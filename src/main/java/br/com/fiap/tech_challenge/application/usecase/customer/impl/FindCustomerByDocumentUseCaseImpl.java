package br.com.fiap.tech_challenge.application.usecase.customer.impl;

import br.com.fiap.tech_challenge.application.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.application.persistence.CustomerPersistence;
import br.com.fiap.tech_challenge.application.usecase.customer.FindCustomerByDocumentUseCase;
import br.com.fiap.tech_challenge.domain.models.Customer;

public class FindCustomerByDocumentUseCaseImpl implements FindCustomerByDocumentUseCase {

	private final CustomerPersistence persistence;

	public FindCustomerByDocumentUseCaseImpl(CustomerPersistence persistence) {
		this.persistence = persistence;
	}

	@Override
	public Customer findByDocument(String document) {
		var customerFound = persistence.findByDocument(document);

		if (customerFound.isEmpty()) {
			throw new DoesNotExistException("Customer Doesn't Exist");
		}

		return customerFound.get();
	}

}
