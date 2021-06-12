package com.bank.runner.application.mapper;

import com.bank.runner.application.dto.ResponseTipoMovimientosDto;
import com.bank.runner.application.entities.TipoMovimiento;

public interface IMapperTipoMovimientos {
	public ResponseTipoMovimientosDto  mostrarTipoMovimientos (TipoMovimiento atributos);
}
