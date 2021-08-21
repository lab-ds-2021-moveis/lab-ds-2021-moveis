package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Product;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.AddCartItemDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.ProductService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
            @RequestParam(value = "name", defaultValue = "") String productName,
            @RequestParam(value = "model", defaultValue = "") String productModel,
            @RequestParam(value = "manufacturer", defaultValue = "") String productManufacturer,
            Model model
    ) {
        ProductSpecification spec = new ProductSpecification(productName, productModel, productManufacturer);

        Page<Product> page = productService.findPageWithSpec(PageRequest.of(pageNumber, size), spec);
        List<String> models = productService.getModels();
        List<String> manufacturers = productService.getManufacturers();

        model.addAttribute("page", page);
        model.addAttribute("spec", spec);
        model.addAttribute("models", models);
        model.addAttribute("manufacturers", manufacturers);

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
