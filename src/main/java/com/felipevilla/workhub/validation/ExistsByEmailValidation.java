package com.felipevilla.workhub.validation;

import org.springframework.stereotype.Component;

import com.felipevilla.workhub.service.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistsByEmailValidation implements ConstraintValidator<ExistsByEmail, String> {

    private final UserService userService;

    public ExistsByEmailValidation(UserService userService){
        this.userService = userService;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userService.existsByEmail(email);
    }

}
