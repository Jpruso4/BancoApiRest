package com.bank.runner.application.mapper.impl;

import org.springframework.stereotype.Service;

import com.bank.runner.application.dto.ResponseTipoDocumentoDto;
import com.bank.runner.application.entities.TipoDocumento;
import com.bank.runner.application.mapper.IMapperTipoDocumento;

@Service
public class MapperTipoDocumento implements IMapperTipoDocumento{
	@Override
	public ResponseTipoDocumentoDto mostrarTipoDocumento(TipoDocumento atributosDocumento) {
		ResponseTipoDocumentoDto mostrarLista = new ResponseTipoDocumentoDto();
		mostrarLista.setIdTipoDocumento(atributosDocumento.getIdTipoDocumento());
		mostrarLista.setTipoDocumento(atributosDocumento.getTipoDocumento());
		return mostrarLista;
	}
}
