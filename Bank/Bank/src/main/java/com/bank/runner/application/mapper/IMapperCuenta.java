package com.bank.runner.application.mapper;

import com.bank.runner.application.dto.ResponseCuentaDto;
import com.bank.runner.application.entities.Cuenta;

public interface IMapperCuenta {
	public ResponseCuentaDto mostrarCuenta (Cuenta atributos);
}
