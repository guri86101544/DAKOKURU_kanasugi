package com.example.demo.validator;

import com.example.demo.annotation.HalfAlphaNumOnly;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HalfAlphaNumValidator implements ConstraintValidator<HalfAlphaNumOnly, String> {
    private static final String REGEX = "^[A-Za-z0-9]+$";
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches(REGEX);
    }
}
