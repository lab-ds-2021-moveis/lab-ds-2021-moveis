package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/internal/dashboard")
public class DashboardController {

    @GetMapping
    public String dashboard() {
        return "internal/dashboard";
    }
}
