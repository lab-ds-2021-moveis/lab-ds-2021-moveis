package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Product;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.AddCartItemDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This controller must have routes to browse products in the shop
 *
 * Every route is public
 *
 */
@Controller
public class ShopController {

    @Autowired
    private ProductService productService;

    @GetMapping({"/", "/shop"})
    public String shopHome(
            @RequestParam(value = "p", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            Model model
    ) {
        Page<Product> page = productService.findPage(PageRequest.of(pageNumber, size));
        model.addAttribute("page", page);
        return "shop/home";
    }

    @GetMapping("/shop/product/{id}")
    public String viewProduct(@ModelAttribute("id") Product product, Model model) {
        if (product == null) {
            return "redirect:/shop";
        }

        AddCartItemDTO addCartItemDTO = new AddCartItemDTO();
        addCartItemDTO.id = product.getId();

        model.addAttribute("product", product);
        model.addAttribute("dto", addCartItemDTO);

        return "shop/product";
    }
}
