package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Customer;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Employee;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Purchase;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.SubmitPurchaseDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.CustomerService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.ProductPurchaseService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.PurchaseService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.SecurityService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.session.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductPurchaseService productPurchaseService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/purchases")
    public String getPurchaseHistory(Model model) {
        List<Purchase> purchases = new ArrayList<>();
        Class userClass = securityService.getCurrentUserClass();

        if (userClass.equals(Employee.class)) {
            Employee employee = (Employee) securityService.getCurrentUser();
            purchases = employee.getPurchases();
        }

        if (userClass.equals(Customer.class)) {
            Customer customer = (Customer) securityService.getCurrentUser();
            purchases = customer.getPurchases();
        }

        model.addAttribute("purchases", purchases);

        return "purchases";
    }

    @PostMapping("/purchase/submit")
    public String submit (
            HttpSession session,
            @ModelAttribute("dto") SubmitPurchaseDTO dto
    ) {
        Purchase purchase = null;
        ShoppingCart cart = (ShoppingCart) session.getAttribute(ShoppingCart.sessionKey);

        if (cart == null || cart.getList().isEmpty()) {
            return "redirect:/shop/cart";
        }

        Class userClass = securityService.getCurrentUserClass();

        if (userClass.equals(Customer.class)) {
            // Cliente está comprando sozinho
            Customer customer = (Customer) securityService.getCurrentUser();
            purchase = purchaseService.saveFromShoppingCart(cart, customer);
        } else if (userClass.equals(Employee.class)) {
            // Cliente está comprando atraves de um funcionário
            Employee employee = (Employee) securityService.getCurrentUser();

            if (dto.customerId == null) {
                return "redirect:/shop/cart";
            }

            Optional<Customer> result = customerService.find(dto.customerId);
            if (result.isPresent()) {
                purchase = purchaseService.saveFromShoppingCart(cart, result.get(), employee);
            }
        }

        if (purchase != null) {
            productPurchaseService.saveFromShoppingCart(cart, purchase);

            // Limpa o carrinho depois de processar a compra
            session.setAttribute(ShoppingCart.sessionKey, new ShoppingCart());
        }

        return "redirect:/purchases";
    }

    @GetMapping("/internal/purchases")
    public String internalListAllPurchases(Model model) {
        model.addAttribute("purchases", purchaseService.findAll());
        return "/internal/purchases/index";
    }

    @GetMapping("/internal/purchases/{id}")
    public String internalGetPurchaseDetails(@PathVariable("id") Long id, Model model) {
        Optional<Purchase> result = purchaseService.find(id);

        if (result.isEmpty()) {
            return "redirect:/internal/purchases";
        }

        model.addAttribute("purchase", result.get());

        return "/internal/purchases/show";
    }

}
