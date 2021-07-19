package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.validation;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.ProductEnvironment;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.UpdateProductDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UpdateProductDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UpdateProductDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UpdateProductDTO dto = (UpdateProductDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "environment", "NotEmpty");

        try {
            ProductEnvironment.valueOf(dto.getEnvironment());
        } catch (EnumConstantNotPresentException e) {
            errors.rejectValue("environment", "Format.form.enum.environment.notFound");
        }
    }
}
