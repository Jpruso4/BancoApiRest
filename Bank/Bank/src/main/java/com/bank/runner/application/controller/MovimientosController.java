package com.bank.runner.application.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bank.runner.application.dto.RequestMovimientosDto;
import com.bank.runner.application.dto.ResponseMensajeDto;
import com.bank.runner.application.dto.ResponseMovimientosDto;
import com.bank.runner.application.service.impl.MovimientosService;
import com.bank.runner.application.util.Constantes;

@RestController
@RequestMapping(Constantes.MOVIMIENTOS_CONTROLLER)
public class MovimientosController {

	private final MovimientosService movimientosService;

	@Autowired
	public MovimientosController(MovimientosService movimientosService) {
		this.movimientosService = movimientosService;

	}
	
	@GetMapping("/export")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
 	public ResponseEntity<String> exportToCsvSimpleString(HttpServletResponse response)throws IOException {
		final String csvData = movimientosService.mostrarCsvDeMovimientos(response);
		
		HttpHeaders headers = new HttpHeaders();
		
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.TEXT_PLAIN)
				.body(csvData);
	}

	@GetMapping(value = "/{idMovimiento}")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseMovimientosDto mostrarMovimiento(
			@Valid @NotNull(message = "The value is required") @PathVariable("idMovimiento") Integer idMovimiento) {
		return movimientosService.mostrarMovimiento(idMovimiento);
	}

	@GetMapping(value = "")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public List<ResponseMovimientosDto> mostrarListaMovimientos() {
		return movimientosService.mostrarListaMovimientos();
	}

	@PostMapping(value = "", produces = "application/json", consumes = "application/json")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseMensajeDto registrarMovimiento(@RequestBody RequestMovimientosDto datosMovimientosNuevos) {
		return movimientosService.registrarMovimiento(datosMovimientosNuevos);
	}

	@PutMapping(produces = "application/json", consumes = "application/json")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseMensajeDto actualizarMovimiento(@RequestBody RequestMovimientosDto requestMovimiento) {
		return movimientosService.actualizarDatos(requestMovimiento);
	}

	@DeleteMapping(value = "/{idMovimiento}")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseMensajeDto eliminarMovimientos(@PathVariable("idMovimiento") Integer idMovimiento) {
		return movimientosService.eliminarMovimiento(idMovimiento);
	}
}
