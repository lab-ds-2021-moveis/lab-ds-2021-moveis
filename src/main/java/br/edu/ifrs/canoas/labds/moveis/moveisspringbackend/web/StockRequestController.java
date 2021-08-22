package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Product;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.StockEntry;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.StockRequest;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.RequestStatus;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.StoreStockRequestDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.UpdateProductDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.UpdateStockRequestDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.ProductService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.StockEntryService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.StockRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping ("internal/stock/request")
public class StockRequestController {

    @Autowired
    private StockRequestService stockRequestService;

    @Autowired
    private ProductService productService;

    @Autowired
    private StockEntryService stockEntryService;

    @GetMapping
    public String listStockRequests(
            @RequestParam(value = "p", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            Model model
    ){
        Page<StockRequest> page = stockRequestService.findPage(PageRequest.of(pageNumber, size));
        model.addAttribute("page", page);
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

    @GetMapping("{id}")
    public String showEditForm(@ModelAttribute("id") StockRequest stockRequest, Model model) {
        if (stockRequest == null) {
            return "redirect:/internal/stock/request";
        }

        UpdateStockRequestDTO dto = new UpdateStockRequestDTO().from(stockRequest);

        model.addAttribute("products", stockRequest);
        model.addAttribute("dto", dto);

        return "internal/stock/request/edit";
    }

    @PostMapping("edit")
    public String edit(@ModelAttribute("dto") UpdateStockRequestDTO dto, Model model) {
        Optional<Product> result = productService.find(dto.idProduct); //Optional<StockRequest> result = stockRequestService.find(dto.id);

        if (result.isEmpty()) {
            return "redirect:/internal/stock/request/edit";
        }
        StockRequest stockRequest = dto.toEntity();
        if(stockRequest.getStatus().equals(RequestStatus.FINISHED)){
            //TODO criar e salvar o StockEntry aqui. Feito, verificar se funciona após fazer o index do stockEntry
            StockEntry stockEntry = new StockEntry();
            stockEntry.setAmount(stockRequest.getAmount());
            stockEntry.setProduct(result.get());
            stockEntryService.save(stockEntry);
        }
        stockRequest.setProduct(result.get());
        stockRequestService.save(stockRequest);

        return "redirect:/internal/stock/request";
    }
}
