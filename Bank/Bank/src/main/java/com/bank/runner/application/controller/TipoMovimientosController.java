package com.bank.runner.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bank.runner.application.dto.RequestTipoMovimientosDto;
import com.bank.runner.application.dto.ResponseMensajeDto;
import com.bank.runner.application.dto.ResponseTipoMovimientosDto;
import com.bank.runner.application.mapper.impl.MapperTipoMovimientos;
import com.bank.runner.application.service.impl.TipoMovimientosService;
import com.bank.runner.application.util.Constantes;

@RestController
@RequestMapping(Constantes.TIPO_MOVIMIENTOS_CONTROLLER)
public class TipoMovimientosController {

	private final TipoMovimientosService tipoMovimientosService;
	private final MapperTipoMovimientos mapperTipoMovimientos;

	@Autowired
	public TipoMovimientosController(TipoMovimientosService tipoMovimientosService,
			MapperTipoMovimientos mapperTipoMovimientos) {
		this.tipoMovimientosService = tipoMovimientosService;
		this.mapperTipoMovimientos = mapperTipoMovimientos;
	}

	@GetMapping(value = "/{idTipoMovimientos}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseTipoMovimientosDto mostrarTipoMovimientos(
			@PathVariable("idTipoMovimientos") Integer idTipoMovimientos) {
		return mapperTipoMovimientos
				.mostrarTipoMovimientos(tipoMovimientosService.mostrarTipoMovimientos(idTipoMovimientos));
	}

	@GetMapping(produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ResponseTipoMovimientosDto> mostrarListaTipoMovimientos() {
		return tipoMovimientosService.mostrarListaTipoMovimientos();
	}

	@PostMapping(value = "", produces = "application/json", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseMensajeDto registrarTipoMovimiento(
			@RequestBody ResponseTipoMovimientosDto datosTipoMovimientosNuevo) {
		return tipoMovimientosService.registrarTipoMovimientos(datosTipoMovimientosNuevo);
	}

	@PutMapping(produces = "application/json", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseMensajeDto actualizarTipoMovimientos(@RequestBody RequestTipoMovimientosDto requestTipoMovimientos) {
		return tipoMovimientosService.actualizarDatos(requestTipoMovimientos);
	}
}
