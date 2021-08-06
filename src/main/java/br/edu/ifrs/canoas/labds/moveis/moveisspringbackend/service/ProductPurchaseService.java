package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.ProductPurchase;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Purchase;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.session.ShoppingCart;

import java.util.List;

public interface ProductPurchaseService extends BaseService<ProductPurchase> {
    List<ProductPurchase> saveFromShoppingCart(ShoppingCart shoppingCart, Purchase purchase);
}
