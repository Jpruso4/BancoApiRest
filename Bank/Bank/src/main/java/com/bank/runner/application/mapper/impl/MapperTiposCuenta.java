package com.bank.runner.application.mapper.impl;

import org.springframework.stereotype.Service;

import com.bank.runner.application.dto.ResponseTiposCuentaDto;
import com.bank.runner.application.entities.TiposCuenta;
import com.bank.runner.application.mapper.IMapperTiposCuenta;

@Service
public class MapperTiposCuenta implements IMapperTiposCuenta{
	@Override
	public ResponseTiposCuentaDto mostrarTiposCuenta(TiposCuenta atributos) {
		ResponseTiposCuentaDto tiposCuentaDto = new ResponseTiposCuentaDto ();
		tiposCuentaDto.setTipo_cuenta(atributos.getTipoCuenta());
		tiposCuentaDto.setDescripcion(atributos.getDescripcion());
		tiposCuentaDto.setEstado(atributos.getEstado());
		return tiposCuentaDto;
	}
}
