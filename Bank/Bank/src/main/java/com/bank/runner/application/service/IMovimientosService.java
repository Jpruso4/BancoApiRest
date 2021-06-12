package com.bank.runner.application.service;

import java.util.List;
import com.bank.runner.application.dto.RequestMovimientosDto;
import com.bank.runner.application.dto.ResponseMensajeDto;
import com.bank.runner.application.dto.ResponseMovimientosDto;


public interface IMovimientosService {
	public ResponseMovimientosDto mostrarMovimiento(Integer idMovimiento);

	List<ResponseMovimientosDto> mostrarListaMovimientos();

	public ResponseMensajeDto registrarMovimiento(RequestMovimientosDto datosMovimientoNuevo);

	public ResponseMensajeDto actualizarDatos(RequestMovimientosDto requestMovimiento);
}

