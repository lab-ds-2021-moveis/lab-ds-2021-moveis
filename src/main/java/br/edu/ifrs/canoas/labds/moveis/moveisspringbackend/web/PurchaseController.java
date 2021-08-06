package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Customer;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Employee;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Purchase;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.SubmitPurchaseDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.ProductPurchaseService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.PurchaseService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.SecurityService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.session.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductPurchaseService productPurchaseService;

    @Autowired
    private SecurityService securityService;

    @PostMapping("submit")
    public String submit (
            HttpSession session,
            @ModelAttribute("dto") SubmitPurchaseDTO dto
    ) {
        Purchase purchase = null;
        ShoppingCart cart = (ShoppingCart) session.getAttribute(ShoppingCart.sessionKey);

        Class userClass = securityService.getCurrentUserClass();

        if (userClass.equals(Customer.class)) {
            // Cliente está comprando sozinho
            Customer customer = (Customer) securityService.getCurrentUser();
            purchase = purchaseService.saveFromShoppingCart(cart, customer);
        } else if (userClass.equals(Employee.class)) {
            // Cliente está comprando atraves de um funcionário
            Employee employee = (Employee) securityService.getCurrentUser();
            // TODO: Pegar o cliente do dto e mandar pro saveFromShoppingCart
            // purchase =
        }

        productPurchaseService.saveFromShoppingCart(cart, purchase);

        // Limpa o carrinho depois de processar a compra
        session.setAttribute(ShoppingCart.sessionKey, new ShoppingCart());

        // TODO: Mandar para a lista de compras
        return "redirect:/shop";
    }

}
