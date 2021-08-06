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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
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

    @PostMapping("/purchase/submit")
    public String submit (
            HttpSession session,
            @ModelAttribute("dto") SubmitPurchaseDTO dto
    ) {
        Purchase purchase = null;
        ShoppingCart cart = (ShoppingCart) session.getAttribute(ShoppingCart.sessionKey);

        if (cart == null || cart.getList().isEmpty() || dto.customerId == null) {
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

        // TODO: Mandar para a lista de compras
        return "redirect:/shop";
    }

    @GetMapping("/internal/purchases")
    public String listAllPurchases(Model model) {
        model.addAttribute("purchases", purchaseService.findAll());
        return "/internal/purchases/index";
    }

}
