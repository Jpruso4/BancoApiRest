package com.bank.runner.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bank.runner.application.dto.ResponseTiposCuentaDto;
import com.bank.runner.application.mapper.impl.MapperTiposCuenta;
import com.bank.runner.application.service.impl.TiposCuentaService;
import com.bank.runner.application.util.Constantes;

@RestController
@RequestMapping(Constantes.TIPOSCUENTA_CONTROLLER)
public class TiposCuentaController {

	private final MapperTiposCuenta mapperTiposCuenta;
	private final TiposCuentaService tiposCuentaService;

	public TiposCuentaController(MapperTiposCuenta mapperTiposCuenta, TiposCuentaService tiposCuentaService) {
		this.mapperTiposCuenta = mapperTiposCuenta;
		this.tiposCuentaService = tiposCuentaService;

	}

	@GetMapping(value = "/{idTipoCuenta}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseTiposCuentaDto mostrarTiposCuenta(@PathVariable("idTipoCuenta") Integer idTipoCuenta) {
		return mapperTiposCuenta.mostrarTiposCuenta(tiposCuentaService.mostrarTipoCuenta(idTipoCuenta));
	}
}
