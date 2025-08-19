package com.example.demo.validator;

import com.example.demo.annotation.HiraganaOnly;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HiraganaValidator implements ConstraintValidator<HiraganaOnly, String> {
    private static final String HIRAGANA_REGEX = "^[\\u3041-\\u3096-]+$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches(HIRAGANA_REGEX);
    }
}
