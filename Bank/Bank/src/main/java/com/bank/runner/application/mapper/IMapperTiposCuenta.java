package com.bank.runner.application.mapper;

import com.bank.runner.application.dto.ResponseTiposCuentaDto;
import com.bank.runner.application.entities.TiposCuenta;

public interface IMapperTiposCuenta {
	public ResponseTiposCuentaDto mostrarTiposCuenta (TiposCuenta atributos);

}
