package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Product;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.StoreProductDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.ProductService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.validation.StoreProductDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/internal/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private StoreProductDTOValidator storeProductDTOValidator;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        return "internal/product/index";
    }

    @GetMapping("add")
    public String showAddForm(Model model) {
        model.addAttribute("dto", new StoreProductDTO());
        return "internal/product/add";
    }

    @PostMapping("add")
    public String add(@ModelAttribute("dto") StoreProductDTO dto, BindingResult bindingResult) {
        storeProductDTOValidator.validate(dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return "internal/product/add";
        }

        productService.save(dto.toEntity());

        return "redirect:/internal/product";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Optional<Product> result = productService.find(id);

        if (result.isEmpty()) {
            return "redirect:/internal/product";
        }

        model.addAttribute("product", result.get());
        return "/internal/product/show";
    }

}
