package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Customer;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Employee;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Mounting;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Purchase;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.SubmitPurchaseDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.*;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.session.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductPurchaseService productPurchaseService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MountingService mountingService;

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

        // Cadastra a montagem
        Optional<Mounting> mounting = Optional.empty();
        if (dto.includeMount) {
            mounting = Optional.of(mountingService.save(dto.buildMounting()));
        }

        // Pega qual usuário está realizando a compra. Se for um funcionário, precisa pegar qual cliente ele selecionou
        Class userClass = securityService.getCurrentUserClass();
        Customer customer = null;
        Optional<Employee> employee = Optional.empty();

        if (userClass.equals(Customer.class)) {
            customer = (Customer) securityService.getCurrentUser();
        }

        if (userClass.equals(Employee.class)) {
            employee = Optional.of((Employee) securityService.getCurrentUser());

            if (dto.customerId == null) {
                return "redirect:/shop/cart";
            }

            Optional<Customer> result = customerService.find(dto.customerId);
            if (result.isPresent()) {
                customer = result.get();
            }
        }

        // Efetiva a compra
        purchase = purchaseService.saveFromShoppingCart(cart, customer, mounting, employee);

        // Cadastra coisas complementares (ligação n para n)
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
