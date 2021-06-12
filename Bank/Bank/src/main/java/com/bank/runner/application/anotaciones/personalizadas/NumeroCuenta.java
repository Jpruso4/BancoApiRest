package com.bank.runner.application.anotaciones.personalizadas;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = NumeroCuentaValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumeroCuenta {
	String message() default "{numeroCuentaValida.mensajePorDefecto}";
	
	Class <?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
