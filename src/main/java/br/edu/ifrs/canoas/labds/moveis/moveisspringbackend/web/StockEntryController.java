package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.StockEntry;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.ProductService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.StockEntryService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.StockRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String listStockEntries(
            @RequestParam(value = "p", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            Model model
    ){
        Page<StockEntry> page = stockEntryService.findPage(PageRequest.of(pageNumber, size));
        model.addAttribute("page", page);
        return "internal/stock/entry/index";
    }


}
