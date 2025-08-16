package com.example.demo.validator;

import com.example.demo.annotation.KatakanaOnly;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class KatakanaValidator implements ConstraintValidator<KatakanaOnly, String> {
    private static final String KATAKANA_REGEX = "^[\\u30A1-\\u30FA-]+$";
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches(KATAKANA_REGEX);
    }
    
}
