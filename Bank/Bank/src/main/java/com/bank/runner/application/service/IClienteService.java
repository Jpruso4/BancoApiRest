package com.bank.runner.application.service;

import java.util.List;

import com.bank.runner.application.dto.ResponseClienteDto;
import com.bank.runner.application.dto.ResponseMensajeDto;
import com.bank.runner.application.dto.RequestClienteDto;

public interface IClienteService {
	public ResponseClienteDto mostrarCliente(Integer idCliente);

	List<ResponseClienteDto> mostrarListaClientes();

	public ResponseMensajeDto registrarCliente(RequestClienteDto datosClienteNuevo);

	public ResponseMensajeDto actualizarEstadoCliente(Integer idCliente);

	public ResponseMensajeDto actualizarDatos(RequestClienteDto requestCliente);
}
