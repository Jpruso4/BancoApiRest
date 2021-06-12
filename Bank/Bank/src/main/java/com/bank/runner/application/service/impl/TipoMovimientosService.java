package com.bank.runner.application.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.runner.application.dao.ITipoMoviemientosDao;
import com.bank.runner.application.dto.RequestTipoMovimientosDto;
import com.bank.runner.application.dto.ResponseMensajeDto;
import com.bank.runner.application.dto.ResponseTipoMovimientosDto;
import com.bank.runner.application.entities.TipoMovimiento;
import com.bank.runner.application.service.ITipoMovimientosService;
import com.bank.runner.application.util.Constantes;
import com.bank.runner.application.util.ValidacionesActualizarServicios;

@Service
public class TipoMovimientosService implements ITipoMovimientosService {

	private final ITipoMoviemientosDao tipoMovimientoDao;

	@Autowired
	public TipoMovimientosService(ITipoMoviemientosDao tipoMoviemientosDao) {
		this.tipoMovimientoDao = tipoMoviemientosDao;
	}

	@Override
	public TipoMovimiento mostrarTipoMovimientos(Integer idTipoMovimientos) {
		return tipoMovimientoDao.getOne(idTipoMovimientos);
	}

	@Override
	public List<ResponseTipoMovimientosDto> mostrarListaTipoMovimientos() {
		List<ResponseTipoMovimientosDto> tipomovimientoDto = new ArrayList<ResponseTipoMovimientosDto>();
		List<TipoMovimiento> tipoMovimientos = new ArrayList<TipoMovimiento>();
		tipoMovimientos = tipoMovimientoDao.findAll();
		for (int i = 0; i < tipoMovimientos.size(); i++) {
			ResponseTipoMovimientosDto objetoresponseTipoMovimientosDto = new ResponseTipoMovimientosDto();
			objetoresponseTipoMovimientosDto.setId_tipo_movimientos(tipoMovimientos.get(i).getIdTipoMovimientos());
			objetoresponseTipoMovimientosDto.setTipos_movimientos(tipoMovimientos.get(i).getTiposMovimientos());
			tipomovimientoDto.add(i, objetoresponseTipoMovimientosDto);
		}

		return tipomovimientoDto;
	}

	@Override
	public ResponseMensajeDto registrarTipoMovimientos(ResponseTipoMovimientosDto datosTipoMovimientoNuevo) {
		ResponseMensajeDto respuestaPeticion = new ResponseMensajeDto();
		TipoMovimiento registroTipoMovimientos = new TipoMovimiento();
		registroTipoMovimientos.setIdTipoMovimientos(datosTipoMovimientoNuevo.getId_tipo_movimientos());
		registroTipoMovimientos.setTiposMovimientos(datosTipoMovimientoNuevo.getTipos_movimientos());
		tipoMovimientoDao.save(registroTipoMovimientos);
		respuestaPeticion.setCodigoRespuesta(Constantes.COD_RESPUESTA_REGISTRO);
		respuestaPeticion.setMensajeRespuesta(Constantes.MENSAJE_REGISTRAR);
		return respuestaPeticion;
	}

	@Override
	public ResponseMensajeDto actualizarDatos(RequestTipoMovimientosDto requestTipoMovimientos) {
		ValidacionesActualizarServicios validaTipoMovimiento = new ValidacionesActualizarServicios();
		ResponseMensajeDto respuestaMensaje = new ResponseMensajeDto();
		Optional<TipoMovimiento> TipoMovimientosData = tipoMovimientoDao
				.findById(requestTipoMovimientos.getIdTipoMovimientos());
		TipoMovimiento datosModificar = TipoMovimientosData.get();
		validaTipoMovimiento.validarTipoMovimiento(datosModificar, requestTipoMovimientos);
		tipoMovimientoDao.save(datosModificar);
		respuestaMensaje.setCodigoRespuesta(Constantes.COD_RESPUESTA_ACTUALIZAR);
		respuestaMensaje.setMensajeRespuesta(Constantes.MENSAJE_DE_ACTUALIZACION_EXITOSA);
		return respuestaMensaje;
	}
}
