package com.bank.runner.application.mapper.impl;

import org.springframework.stereotype.Service;

import com.bank.runner.application.dto.ResponseBancoDto;
import com.bank.runner.application.entities.Banco;
import com.bank.runner.application.mapper.IMapperBanco;

@Service
public class MapperBanco implements IMapperBanco {

	@Override
	public ResponseBancoDto listarBancos(Banco listaBancos) {
		ResponseBancoDto cargarLista = new ResponseBancoDto();
		cargarLista.setId_banco(listaBancos.getIdBanco());
		cargarLista.setNombre_banco(listaBancos.getNombreBanco());
		return cargarLista;
	}
}
