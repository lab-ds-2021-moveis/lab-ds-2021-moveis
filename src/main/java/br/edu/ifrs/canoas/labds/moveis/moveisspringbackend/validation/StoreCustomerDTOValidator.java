package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.validation;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Customer;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.StoreCustomerDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StoreCustomerDTOValidator implements Validator {

    @Autowired
    private CustomerService customerService;

    @Override
    public boolean supports(Class<?> aClass) {
        return StoreCustomerDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        StoreCustomerDTO dto = (StoreCustomerDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpf", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty");

        // TODO: Uma validação de endereço de email

        if (customerService.findByEmail(dto.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.form.email");
        }

        if (dto.getPassword().length() < 6) {
            errors.rejectValue("password", "Size.form.password");
        }

        if (!dto.getPasswordConfirm().equals(dto.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.form.passwordConfirm");
        }

    }
}
