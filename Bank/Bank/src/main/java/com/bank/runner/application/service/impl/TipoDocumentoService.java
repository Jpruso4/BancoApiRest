package com.bank.runner.application.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.runner.application.dao.ITipoDocumentoDao;
import com.bank.runner.application.dto.RequestTipoDocumentoDto;
import com.bank.runner.application.dto.ResponseMensajeDto;
import com.bank.runner.application.dto.ResponseTipoDocumentoDto;
import com.bank.runner.application.entities.TipoDocumento;
import com.bank.runner.application.mapper.impl.MapperTipoDocumento;
import com.bank.runner.application.service.ITipoDocumentoService;
import com.bank.runner.application.util.Constantes;
import com.bank.runner.application.util.ValidacionesActualizarServicios;

@Service
public class TipoDocumentoService implements ITipoDocumentoService{
	private final ITipoDocumentoDao tipoDocumentoDao;
	final MapperTipoDocumento mapperTipoDocumento;

	@Autowired
	public TipoDocumentoService(ITipoDocumentoDao tipoDocumentoDao, MapperTipoDocumento mapperTipoDocumento) {
		this.tipoDocumentoDao = tipoDocumentoDao;
		this.mapperTipoDocumento = mapperTipoDocumento;
	}

	@Override
	public ResponseTipoDocumentoDto mostrarTipoDocumento(Integer idTipoDocumento) {	
		Optional<TipoDocumento> tipoDocumentoData = tipoDocumentoDao.findById(idTipoDocumento);
		
		if (!tipoDocumentoData.isPresent()) {
			throw new NoSuchElementException(Constantes.MENSAJE_NULO);
		}
		TipoDocumento tipoDocumento = tipoDocumentoData.get();
		ResponseTipoDocumentoDto responseTipoDocumento = mapperTipoDocumento.mostrarTipoDocumento(tipoDocumento);
		return responseTipoDocumento;
	}

	@Override
	public List<ResponseTipoDocumentoDto> mostrarListaTiposDocumentos() {
		List<ResponseTipoDocumentoDto> tipoDocumentos = new LinkedList<>();
		List<TipoDocumento> tipoDocumentoEntities = tipoDocumentoDao.findAll();
		for (TipoDocumento tipoDocumento : tipoDocumentoEntities) {
			tipoDocumentos.add(mapperTipoDocumento.mostrarTipoDocumento(tipoDocumento));
		}
		return tipoDocumentos;
	}

	@Override
	public ResponseMensajeDto registrarTipoDocumento(ResponseTipoDocumentoDto tipoDocumentoNuevo) {
		ResponseMensajeDto respuestaPeticion = new ResponseMensajeDto();
		TipoDocumento registroTipoDocumento = new TipoDocumento();

		registroTipoDocumento.setIdTipoDocumento(tipoDocumentoNuevo.getIdTipoDocumento());
		registroTipoDocumento.setTipoDocumento(tipoDocumentoNuevo.getTipoDocumento());
		tipoDocumentoDao.save(registroTipoDocumento);
		respuestaPeticion.setCodigoRespuesta(Constantes.COD_RESPUESTA_REGISTRO);
		respuestaPeticion.setMensajeRespuesta(Constantes.MENSAJE_REGISTRAR);
		return respuestaPeticion;
	}

	@Override
	public ResponseMensajeDto actualizarTipoDocumento(RequestTipoDocumentoDto requestTipoDocumento) {
		ValidacionesActualizarServicios validarTipoDocumento = new ValidacionesActualizarServicios();
		ResponseMensajeDto respuestaMensaje = new ResponseMensajeDto();
		Optional<TipoDocumento> tipoDocumentoData = tipoDocumentoDao.findById(requestTipoDocumento.getIdTipoDocumento());
		TipoDocumento datosModificar = tipoDocumentoData.get();
		validarTipoDocumento.validarTipoDocumento(datosModificar, requestTipoDocumento);
		tipoDocumentoDao.save(datosModificar);
		respuestaMensaje.setCodigoRespuesta(Constantes.COD_RESPUESTA_ACTUALIZAR);
		respuestaMensaje.setMensajeRespuesta(Constantes.MENSAJE_DE_ACTUALIZACION_EXITOSA);
		return respuestaMensaje;
	}

	@Override
	public ResponseMensajeDto eliminarTipoDocumento(Integer idTipoDocumento) {
		ResponseMensajeDto respuestaMensaje = new ResponseMensajeDto();
		if(tipoDocumentoDao.findById(idTipoDocumento)!= null){
			tipoDocumentoDao.deleteById(idTipoDocumento);
			respuestaMensaje.setCodigoRespuesta(Constantes.COD_RESPUESTA_ELIMINAR_TIPODOCUMENTO);
			respuestaMensaje.setMensajeRespuesta(Constantes.MENSAJE_ELIMINACION_EXITOSA);
		}
		return respuestaMensaje;
	}
}
