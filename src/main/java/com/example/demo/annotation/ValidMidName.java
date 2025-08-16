package com.example.demo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.demo.validator.MidNameConsistencyValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = MidNameConsistencyValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMidName {
    String message() default "ミドルネームの各欄に一つでも入力があった場合は必須です。";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
