package com.bank.runner.application.mapper.impl;

import org.springframework.stereotype.Service;
import com.bank.runner.application.dto.ResponseTipoMovimientosDto;
import com.bank.runner.application.entities.TipoMovimiento;
import com.bank.runner.application.mapper.IMapperTipoMovimientos;


@Service
public class MapperTipoMovimientos implements IMapperTipoMovimientos{
	
	@Override
	public ResponseTipoMovimientosDto mostrarTipoMovimientos(TipoMovimiento atributos) {
		ResponseTipoMovimientosDto mostrarLista = new ResponseTipoMovimientosDto();
		mostrarLista.setId_tipo_movimientos(atributos.getIdTipoMovimientos());
		mostrarLista.setTipos_movimientos(atributos.getTiposMovimientos());
		return mostrarLista;
	}
}