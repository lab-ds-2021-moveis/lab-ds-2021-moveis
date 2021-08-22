package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Employee;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.StoreEmployeeDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.EmployeeService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.validation.StoreEmployeeDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String list(
            @RequestParam(value = "p", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            Model model
    ) {
        Page<Employee> page = employeeService.findPage(PageRequest.of(pageNumber, size));
        model.addAttribute("page", page);
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
