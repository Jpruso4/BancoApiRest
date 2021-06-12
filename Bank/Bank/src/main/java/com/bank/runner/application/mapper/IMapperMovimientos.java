package com.bank.runner.application.mapper;

import com.bank.runner.application.dto.ResponseMovimientosDto;
import com.bank.runner.application.entities.Movimiento;

public interface IMapperMovimientos {
	public ResponseMovimientosDto mostrarMovimiento(Movimiento atributos);
}
