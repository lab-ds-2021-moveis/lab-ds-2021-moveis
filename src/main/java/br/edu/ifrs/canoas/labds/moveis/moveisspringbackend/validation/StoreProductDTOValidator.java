package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.validation;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.ProductEnvironment;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.StoreProductDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StoreProductDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return StoreProductDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        StoreProductDTO dto = (StoreProductDTO) o;

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
