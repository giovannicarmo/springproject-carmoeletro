package com.giovannicarmo.projetocurso.carmoeletro.services.validation;

import com.giovannicarmo.projetocurso.carmoeletro.services.validation.ClientInsertValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ClientInsertValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ClientUpdate {
    String message() default "Validation error.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
