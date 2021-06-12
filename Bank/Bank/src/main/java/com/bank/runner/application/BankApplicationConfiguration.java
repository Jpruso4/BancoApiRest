package com.bank.runner.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.bank.runner.application.util.Validaciones;
import com.bank.runner.application.util.ValidacionesActualizarServicios;

@Configuration
public class BankApplicationConfiguration {
	@Bean
	public Validaciones getValidaciones() {
		return new Validaciones();
	}
	@Bean
	public ValidacionesActualizarServicios getValidacionesActualizarServicios() {
		return new ValidacionesActualizarServicios();
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
