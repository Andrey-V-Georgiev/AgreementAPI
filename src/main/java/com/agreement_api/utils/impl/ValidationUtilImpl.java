package com.agreement_api.utils.impl;

import com.agreement_api.models.binding.AgreementBindingModel;
import com.agreement_api.utils.ValidationUtil;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidationUtilImpl implements ValidationUtil {

    private Validator validator;

    public ValidationUtilImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <T> boolean isValid(T entity) {
        return this.validator.validate(entity).isEmpty();
    }

    @Override
    public <T> Set<ConstraintViolation<T>> violations(T entity) {
        return this.validator.validate(entity);
    }

    @Override
    public String violationsString(Set<ConstraintViolation<AgreementBindingModel>> violations) {
        StringBuilder sb = new StringBuilder();
        violations.forEach(violation -> sb.append(violation).append(System.lineSeparator()));
        return sb.toString();
    }
}