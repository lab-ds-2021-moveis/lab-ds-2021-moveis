package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.impl;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Product;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.ProductPurchase;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Purchase;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.repository.ProductPurchaseRepository;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.repository.ProductRepository;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.ProductPurchaseService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.session.CartItem;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.session.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductPurchaseServiceImpl extends BaseServiceImpl<ProductPurchase> implements ProductPurchaseService {

    @Autowired
    private ProductPurchaseRepository productPurchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductPurchase> saveFromShoppingCart(ShoppingCart shoppingCart, Purchase purchase) {
        List<ProductPurchase> productPurchases = new ArrayList<>();

        for (CartItem cartItem : shoppingCart.getList()) {
            ProductPurchase productPurchase = new ProductPurchase();

            Optional<Product> result = productRepository.findById(cartItem.getId());
            result.ifPresent(productPurchase::setProduct);

            productPurchase.setPurchase(purchase);
            productPurchase.setAmount(cartItem.getAmount().intValue());

            productPurchases.add(productPurchase);
        }

        return productPurchaseRepository.saveAll(productPurchases);
    }
}
