package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.demo.validator.UniqueEmployeeNoValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmployeeNoValidator.class)
public @interface UniqueEmployeeNo {
    String message() default "社員番号が既に存在しています。";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
