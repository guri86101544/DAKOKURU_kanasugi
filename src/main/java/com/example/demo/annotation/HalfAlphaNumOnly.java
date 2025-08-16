package com.example.demo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.demo.validator.HalfAlphaNumValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

    @Documented
    @Constraint(validatedBy = HalfAlphaNumValidator.class)
    @Target({ ElementType.FIELD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface HalfAlphaNumOnly {
        String message();
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

