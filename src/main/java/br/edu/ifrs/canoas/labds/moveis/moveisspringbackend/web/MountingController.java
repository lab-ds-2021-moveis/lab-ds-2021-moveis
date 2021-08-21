package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Mounting;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Product;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.StockEntry;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.StockRequest;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.MountingStatus;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.RequestStatus;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.StoreMountingDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.StoreStockRequestDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.UpdateMountingDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.UpdateStockRequestDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.MountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("internal/mounting")
public class MountingController {

    @Autowired
    private MountingService mountingService;

    @GetMapping
    public String listMountings(Model model){
        model.addAttribute("mountings", mountingService.findAll());
        return "internal/mounting/index";
    }

    @GetMapping ("add")
    public String showAddForm (Model model){
        model.addAttribute("dto", new StoreMountingDTO());
        return "internal/mounting/add";
    }

    @PostMapping("add")
    public String add (@ModelAttribute("dto") StoreMountingDTO dto){
        Mounting mounting = dto.toEntity();
        mountingService.save(mounting);
        return "redirect:/shop/cart";
    }

    @GetMapping("{id}")
    public String showEditForm(@ModelAttribute("id") Mounting mounting, Model model) {
        if (mounting == null) {
            return "redirect:/internal/mounting";
        }

        UpdateMountingDTO dto = new UpdateMountingDTO().from(mounting);

        model.addAttribute("dto", dto);

        return "internal/mounting/edit";
    }

    @PostMapping("edit")
    public String edit(@ModelAttribute("dto") UpdateMountingDTO dto, Model model) {
        Mounting mounting = dto.toEntity();

        mountingService.save(mounting);

        return "redirect:/internal/mounting";
    }
}
