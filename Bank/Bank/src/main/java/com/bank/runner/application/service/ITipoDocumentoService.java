package com.bank.runner.application.service;

import java.util.List;

import com.bank.runner.application.dto.RequestTipoDocumentoDto;
import com.bank.runner.application.dto.ResponseMensajeDto;
import com.bank.runner.application.dto.ResponseTipoDocumentoDto;

public interface ITipoDocumentoService {
	public ResponseTipoDocumentoDto mostrarTipoDocumento(Integer idTipoDocumento);
	List<ResponseTipoDocumentoDto> mostrarListaTiposDocumentos();
	public ResponseMensajeDto registrarTipoDocumento(ResponseTipoDocumentoDto tipoDocumentoNuevo);
	public ResponseMensajeDto actualizarTipoDocumento(RequestTipoDocumentoDto requestTipoDocumento);
	public ResponseMensajeDto eliminarTipoDocumento(Integer idTipoDocumento);
}
