package com.example.demo.validator;

import java.util.regex.Pattern;

import com.example.demo.annotation.ZenkakuOnly;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ZenkakuValidator implements ConstraintValidator<ZenkakuOnly, String> {
    private static final Pattern ZENKAKU_PATTERN = Pattern.compile("^[\\u3000-\\uFFFD]+$");
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || ZENKAKU_PATTERN.matcher(value).matches();
    }

}
