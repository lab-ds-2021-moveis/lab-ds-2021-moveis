package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Customer;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Employee;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Mounting;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Purchase;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.session.ShoppingCart;

import java.util.Optional;

public interface PurchaseService extends BaseService<Purchase> {
    Purchase saveFromShoppingCart(ShoppingCart shoppingCart, Customer customer, Optional<Mounting> mounting, Optional<Employee> employee);
}
