package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.impl;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.*;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.repository.ProductRepository;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.repository.PurchaseRepository;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.PurchaseService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.session.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PurchaseServiceImpl extends BaseServiceImpl<Purchase> implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Purchase saveFromShoppingCart(ShoppingCart shoppingCart, Customer customer, Optional<Mounting> mounting, Optional<Employee> employee) {
        Purchase purchase = new Purchase();

        purchase.setTotalValue(shoppingCart.getTotal());
        purchase.setCustomer(customer);

        if (employee.isPresent()) {
            purchase.setEmployee(employee.get());
        }

        if (mounting.isPresent()) {
            purchase.setMounting(mounting.get());
        }

        return purchaseRepository.save(purchase);
    }
}
