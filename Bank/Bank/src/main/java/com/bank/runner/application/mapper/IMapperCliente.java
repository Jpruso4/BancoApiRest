package com.bank.runner.application.mapper;

import com.bank.runner.application.dto.ResponseClienteDto;
import com.bank.runner.application.entities.Cliente;

public interface IMapperCliente {
	public ResponseClienteDto mostrarCliente (Cliente atributos);
}

