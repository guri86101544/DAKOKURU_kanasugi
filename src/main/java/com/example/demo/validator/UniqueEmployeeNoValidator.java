package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.annotation.UniqueEmployeeNo;
import com.example.demo.service.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueEmployeeNoValidator implements ConstraintValidator<UniqueEmployeeNo, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String employeeNoStr, ConstraintValidatorContext context) {
        if (employeeNoStr == null || employeeNoStr.trim().isEmpty())
            return true;
        try {
            Long employeeNo = Long.parseLong(employeeNoStr);
            return userService.findByEmployeeNo(employeeNo) == null;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
