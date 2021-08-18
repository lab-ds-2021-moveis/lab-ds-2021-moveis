package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Product;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.AddCartItemDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.SubmitPurchaseDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.CustomerService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.session.CartItem;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.session.ShoppingCart;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/shop/cart")
public class ShoppingCartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String show(HttpSession session, Model model) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute(ShoppingCart.sessionKey);
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute(ShoppingCart.sessionKey, cart);
        }
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("dto", new SubmitPurchaseDTO());
        return "shop/cart/show";
    }

    @PostMapping("add")
    public String add(
            HttpSession session,
            HttpServletRequest request,
            @ModelAttribute("dto") AddCartItemDTO addCartItemDTO
    ) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute(ShoppingCart.sessionKey);
        if (cart == null) {
            cart = new ShoppingCart();
        }

        Optional<Product> result = productService.find(addCartItemDTO.id);

        if (result.isEmpty()) {
            // TODO: Jogar um erro (fazer isso em um validator?)
            return "/shop";
        }

        Product product = result.get();
        CartItem cartItem = addCartItemDTO.toEntity();

        cartItem.setValue(product.getValue());
        cartItem.setName(product.getName());

        cart.add(cartItem);

        session.setAttribute(ShoppingCart.sessionKey, cart);
        return "redirect:/shop/cart/";
    }

    @GetMapping("remove/{id}")
    public String remove(HttpSession session, @PathVariable("id") Long id) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute(ShoppingCart.sessionKey);
        if (cart == null) {
            cart = new ShoppingCart();
        }

        cart.remove(id);

        session.setAttribute(ShoppingCart.sessionKey, cart);
        return "redirect:/shop/cart/";
    }

    @GetMapping("clear")
    public String clear(HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute(ShoppingCart.sessionKey);
        if (cart == null) {
            cart = new ShoppingCart();
        }

        cart.clear();

        session.setAttribute(ShoppingCart.sessionKey, cart);
        return "redirect:/shop/cart/";
    }
}
