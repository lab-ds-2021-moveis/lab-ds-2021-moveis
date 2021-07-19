package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Product;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.StoreProductDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.UpdateProductDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.ProductService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.validation.StoreProductDTOValidator;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.validation.UpdateProductDTOValidator;
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

    @Autowired
    private UpdateProductDTOValidator updateProductDTOValidator;

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
    public String showEditForm(@ModelAttribute("id") Product product, Model model) {
        if (product == null) {
            return "redirect:/internal/product";
        }

        UpdateProductDTO dto = new UpdateProductDTO().from(product);

        model.addAttribute("product", product);
        model.addAttribute("dto", dto);

        return "internal/product/edit";
    }

    @PostMapping("edit")
    public String edit(@ModelAttribute("dto") UpdateProductDTO dto, BindingResult bindingResult, Model model) {
        Optional<Product> result = productService.find(dto.id);

        if (result.isEmpty()) {
            return "redirect:/internal/product";
        }

        // Precisa disso pra mostrar o nome original do produto
        model.addAttribute("product", result.get());

        updateProductDTOValidator.validate(dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return "internal/product/edit";
        }

        productService.save(dto.toEntity());

        return "redirect:/internal/product";
    }

    @GetMapping("remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        productService.delete(id);
        return "redirect:/internal/product";
    }
}
