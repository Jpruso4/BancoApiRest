package com.bank.runner.application.service;

import java.util.List;

import com.bank.runner.application.dto.RequestCuentaDto;
import com.bank.runner.application.dto.ResponseCuentaDto;
import com.bank.runner.application.dto.ResponseMensajeDto;

public interface ICuentaService {
	public ResponseCuentaDto mostrarCuenta(Integer idCuenta);

	List<ResponseCuentaDto> mostrarListaCuenta();

	public ResponseMensajeDto registrarCuenta(RequestCuentaDto datosCuentaNueva);

	public ResponseMensajeDto actualizarEstadoCuenta(Integer idCuenta);

	public ResponseMensajeDto actualizarDatosCuenta(RequestCuentaDto requestCuenta);
}
