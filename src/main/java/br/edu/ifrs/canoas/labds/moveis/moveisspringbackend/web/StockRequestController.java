package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Product;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.StockRequest;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.StoreStockRequestDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.ProductService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.StockRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping ("internal/stock/request")
public class StockRequestController {

    @Autowired
    private StockRequestService stockRequestService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listStockRequests(Model model){
        model.addAttribute("stockRequests", stockRequestService.findAll());
        return "internal/stock/request/index";
    }

    @GetMapping ("add")
    public String showAddForm (Model model){
        model.addAttribute("dto", new StoreStockRequestDTO());
        model.addAttribute("products", productService.findAll());
        return "internal/stock/request/add";
    }

    @PostMapping ("add")
    public String add (@ModelAttribute ("dto") StoreStockRequestDTO dto){
        Optional <Product> result = productService.find(dto.idProduct);
        if(result.isEmpty()){
            return "internal/stock/request/add";
        }
        StockRequest stockRequest = dto.toEntity();
        stockRequest.setProduct(result.get());
        stockRequestService.save(stockRequest);
        return "redirect:/internal/stock/request";
    }
}
