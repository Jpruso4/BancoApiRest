package com.bank.runner.application.mapper.impl;

import com.bank.runner.application.entities.Parametria;
import com.bank.runner.application.mapper.IMapperParametria;
import com.bank.runner.application.models.ParametriaModel;

public class MapperParametria implements IMapperParametria{
	
	@Override
	public ParametriaModel mostrarParametria (Parametria parametria) {
		ParametriaModel mostrarLista = new ParametriaModel();
		mostrarLista.setIdParametria(parametria.getIdParametria());
		mostrarLista.setUrl(parametria.getUrl());
		mostrarLista.setSeccion(parametria.getSeccion());
		mostrarLista.setSubseccion(parametria.getSubseccion());
		mostrarLista.setValor(parametria.getValor());
		return mostrarLista;
	}
}
