package com.bank.runner.application.anotaciones.personalizadas;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumeroCuentaValidator implements ConstraintValidator<NumeroCuenta, String> {
	
	private static final int caracteresMax = 11;
	private static final int caracteresMin = 10;

	@Override
	public boolean isValid(String numeroCuenta, ConstraintValidatorContext context) {
		
		int caracteres = numeroCuenta.length();
		
		return caracteres >= caracteresMin && caracteres <= caracteresMax;
	}

}
