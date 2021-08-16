package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/internal/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("purchasesSumByDay", dashboardService.purchasesSumByDay());
        model.addAttribute("purchaseSummary", dashboardService.getPurchaseSummary());
        return "internal/dashboard";
    }
}
