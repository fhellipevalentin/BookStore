package io.github.fhellipe.bookstore.services.validation;

import io.github.fhellipe.bookstore.services.validation.UsuarioUpdateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UsuarioUpdateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

public @interface UsuarioUpdate {

    String message() default "Erro de validação";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
