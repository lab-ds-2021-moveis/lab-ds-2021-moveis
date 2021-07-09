package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {
    @GetMapping("/internal")
    public String home(Model model) {
        return "internal/index";
    }
}
