package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Customer;

import java.util.Optional;

public interface CustomerService extends BaseService<Customer> {
    Customer findByEmail(String email);
}
