package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.validation;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.EmployeeRole;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.StoreCustomerDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.StoreEmployeeDTO;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.CustomerService;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StoreEmployeeDTOValidator implements Validator {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public boolean supports(Class<?> aClass) {
        return StoreEmployeeDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        StoreEmployeeDTO dto = (StoreEmployeeDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "credential", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "NotEmpty");

        try {
            EmployeeRole.valueOf(dto.getRole());
        } catch (EnumConstantNotPresentException e) {
            errors.rejectValue("credential", "Format.form.role");
        }

        if (dto.getCredential().length() < 6) {
            errors.rejectValue("credential", "Size.form.credential");
        }

        if (employeeService.findByCredential(dto.getCredential()) != null) {
            errors.rejectValue("credential", "Duplicate.form.credential");
        }

        if (dto.getPassword().length() < 6) {
            errors.rejectValue("password", "Size.form.password");
        }

        if (!dto.getPasswordConfirm().equals(dto.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.form.passwordConfirm");
        }

    }
}
