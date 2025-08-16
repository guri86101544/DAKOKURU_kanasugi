package com.example.demo.validator;

import com.example.demo.annotation.HalfAlphaOnly;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HalfAlphaValidator implements ConstraintValidator<HalfAlphaOnly, String> {
    private static final String HALF_ALPHA_REGEX = "^[A-Za-z]+$";
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches(HALF_ALPHA_REGEX);
    }
}