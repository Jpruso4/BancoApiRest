package com.bank.runner.application.service;

import java.util.List;

import com.bank.runner.application.dto.RequestTipoMovimientosDto;
import com.bank.runner.application.dto.ResponseMensajeDto;
import com.bank.runner.application.dto.ResponseTipoMovimientosDto;
import com.bank.runner.application.entities.TipoMovimiento;

public interface ITipoMovimientosService {
	public TipoMovimiento mostrarTipoMovimientos(Integer idTipoMovimientos);

	public List<ResponseTipoMovimientosDto> mostrarListaTipoMovimientos();

	public ResponseMensajeDto registrarTipoMovimientos(ResponseTipoMovimientosDto datosTipoMovimientoNuevo);

	public ResponseMensajeDto actualizarDatos(RequestTipoMovimientosDto requestTipoMovimientos);
}


