package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.StoreEmployeeDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.EmployeeService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.validation.StoreEmployeeDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private StoreEmployeeDTOValidator storeEmployeeDTOValidator;

    @GetMapping("/internal")
    public String home(Model model) {
        return "internal/index";
    }

    @GetMapping("/internal/employee")
    public String list(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "internal/employee/index";
    }

    @GetMapping("/internal/employee/add")
    public String addShowForm(Model model) {
        model.addAttribute("employeeForm", new StoreEmployeeDTO());
        return "internal/employee/add";
    }

    @PostMapping("/internal/employee/add")
    public String add(@ModelAttribute("employeeForm") StoreEmployeeDTO employeeForm,  BindingResult bindingResult) {
        storeEmployeeDTOValidator.validate(employeeForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/internal/employee/add";
        }

        employeeService.save(employeeForm.toEntity());

        return "redirect:/internal/employee";
    }
}
