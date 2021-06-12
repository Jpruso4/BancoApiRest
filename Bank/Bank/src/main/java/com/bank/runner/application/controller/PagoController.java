package com.bank.runner.application.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bank.runner.application.dto.ResponseMensajeDto;
import com.bank.runner.application.models.FacturaModel;
import com.bank.runner.application.service.impl.PagoService;
import com.bank.runner.application.util.Constantes;

@RestController
@RequestMapping(Constantes.PAGO_CONTROLLER)
public class PagoController {
	
	private final PagoService pagoService;
	
	@Autowired
	public PagoController(PagoService pagoService) {
		this.pagoService = pagoService;
	}
	
	@GetMapping(value = "/{referencia}")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public FacturaModel consultarFactura(@Valid @NotNull(message = "The value is required") @PathVariable("referencia") Integer referencia) {
		return pagoService.consultarFactura(referencia);
	}
	
	@PostMapping(value = "", produces = "application/json", consumes = "application/json")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseMensajeDto registrarPago(@RequestBody FacturaModel datoPagoNuevo) {
		return pagoService.registrarPago(datoPagoNuevo);
	}
}
