package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.web.rest;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.StoreCustomerDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.CustomerService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.SecurityService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.validation.StoreCustomerDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private StoreCustomerDTOValidator storeCustomerDTOValidator;

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("customerForm", new StoreCustomerDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("customerForm") StoreCustomerDTO customerForm, BindingResult bindingResult) {
        storeCustomerDTOValidator.validate(customerForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        customerService.save(customerForm.toEntity());

        try {
            securityService.autoLogin(customerForm.getEmail(), customerForm.getPasswordConfirm());
        } catch (UsernameNotFoundException e) {
            return "register";
        }

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }

    @GetMapping("/customers")
    public String showCustomerList(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "customers";
    }
}
