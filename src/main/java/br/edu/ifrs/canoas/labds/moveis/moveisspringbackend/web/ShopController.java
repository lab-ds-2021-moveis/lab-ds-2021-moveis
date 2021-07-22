package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller must have routes to browse products in the shop
 *
 * Every route is public
 *
 * TODO: Implement list product route
 * TODO: Implement product view route
 */
@Controller
public class ShopController {

    @Autowired
    private ProductService productService;

    @GetMapping({"/", "/shop"})
    public String shopHome(Model model) {
        model.addAttribute("products", productService.findAll());
        return "shop/home";
    }
}
