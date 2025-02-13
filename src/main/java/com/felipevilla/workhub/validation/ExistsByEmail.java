package com.felipevilla.workhub.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ExistsByEmailValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public  @interface ExistsByEmail {

    String message() default "It already exists in the database, choose another email";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
