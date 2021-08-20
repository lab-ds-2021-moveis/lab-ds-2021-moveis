package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.ProductService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.StockEntryService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.StockRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("internal/stock/entry")
public class StockEntryController {

    @Autowired
    private StockRequestService stockRequestService;

    @Autowired
    private StockEntryService stockEntryService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listStockEntries(Model model){
        model.addAttribute("stockEntries", stockEntryService.findAll());
        return "internal/stock/entry/index";
    }


}
