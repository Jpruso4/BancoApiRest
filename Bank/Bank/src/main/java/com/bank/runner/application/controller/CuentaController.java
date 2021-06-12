package com.bank.runner.application.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bank.runner.application.dto.RequestCuentaDto;
import com.bank.runner.application.dto.ResponseCuentaDto;
import com.bank.runner.application.dto.ResponseMensajeDto;
import com.bank.runner.application.service.impl.CuentaService;
import com.bank.runner.application.util.Constantes;


@RestController
@RequestMapping(Constantes.CUENTA_CONTROLLER)
@Validated
public class CuentaController {

	private final CuentaService cuentaService;

	@Autowired
	public CuentaController(CuentaService cuentaService) {
		this.cuentaService = cuentaService;
	}

	@GetMapping(value = "/{idCuenta}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseCuentaDto mostrarCuenta(@Valid /*@VAlidarCuenta(message="cuenta invalid") @NotBlank(message = "Campo no valido")*/  @NotNull(message = "The value is required") @PathVariable("idCuenta") Integer idCuenta) {
		if (idCuenta == null) {
			throw new RuntimeException();
		}
		
		return cuentaService.mostrarCuenta(idCuenta);
	}

	@GetMapping(value = "")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ResponseCuentaDto> mostrarListaCuenta() {
		return cuentaService.mostrarListaCuenta();
	}

	@PostMapping(value = "", produces = "application/json", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseMensajeDto registrarCuenta(@RequestBody RequestCuentaDto datosCuentaNueva) {
		return cuentaService.registrarCuenta(datosCuentaNueva);
	}

	@PatchMapping(value = "/{idCuenta}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseMensajeDto estadoCuenta(@PathVariable("idCuenta") Integer idCuenta) {
		return cuentaService.actualizarEstadoCuenta(idCuenta);
	}

	@PutMapping(produces = "application/json", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseMensajeDto actualizarCuenta(@RequestBody RequestCuentaDto requestCuenta) {
		return cuentaService.actualizarDatosCuenta(requestCuenta	);
	}
}
