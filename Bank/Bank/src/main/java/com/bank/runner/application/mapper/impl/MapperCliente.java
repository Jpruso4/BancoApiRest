package com.bank.runner.application.mapper.impl;

import org.springframework.stereotype.Service;

import com.bank.runner.application.dto.ResponseClienteDto;
import com.bank.runner.application.entities.Cliente;
import com.bank.runner.application.entities.TipoDocumento;
import com.bank.runner.application.mapper.IMapperCliente;
import com.bank.runner.application.models.TipoDocumentoModel;


@Service
public class MapperCliente implements IMapperCliente{
	
	@Override
	public ResponseClienteDto mostrarCliente(Cliente atributos) {
		ResponseClienteDto mostrarLista = new ResponseClienteDto();
		TipoDocumento tipodocumento = atributos.getTipoDocumento();
		TipoDocumentoModel tipoDocumentoModel = new TipoDocumentoModel();
		mostrarLista.setIdCliente(atributos.getIdCliente());
		tipoDocumentoModel.setIdTipoDocumento(tipodocumento.getIdTipoDocumento());
		tipoDocumentoModel.setTipoDocumento(tipodocumento.getTipoDocumento());
		mostrarLista.setNumeroDocumento(atributos.getNumeroDocumento());
		mostrarLista.setPrimerNombre(atributos.getPrimerNombre());
		mostrarLista.setSegundoNombre(atributos.getSegundoNombre());
		mostrarLista.setPrimerApellido(atributos.getPrimerApellido());
		mostrarLista.setSegundoApellido(atributos.getSegundoApellido());
		mostrarLista.setDireccion(atributos.getDireccion());
		mostrarLista.setTelefono(atributos.getTelefono());
		mostrarLista.setCelular(atributos.getCelular());
		mostrarLista.setCorreo(atributos.getCorreo());
		mostrarLista.setEstado(atributos.getEstado());
		mostrarLista.setTipoDocumento(tipoDocumentoModel);
		return mostrarLista;
	}

}
