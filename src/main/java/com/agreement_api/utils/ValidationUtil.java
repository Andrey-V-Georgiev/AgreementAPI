package com.agreement_api.utils;

import com.agreement_api.models.binding.AgreementBindingModel;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidationUtil {

    <T> boolean isValid(T entity);

    <T> Set<ConstraintViolation<T>> violations(T entity);

    String violationsString(Set<ConstraintViolation<AgreementBindingModel>> violations);
}
