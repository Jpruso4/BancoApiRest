package com.bank.runner.application.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.bank.runner.application.dao.IBancoDao;
import com.bank.runner.application.entities.Banco;
import com.bank.runner.application.mapper.impl.MapperBanco;
import com.bank.runner.application.service.IBancoService;
import com.bank.runner.application.util.Constantes;
import com.bank.runner.application.util.ValidacionesActualizarServicios;
import com.bank.runner.application.dto.RequestBancoDto;
import com.bank.runner.application.dto.ResponseBancoDto;
import com.bank.runner.application.dto.ResponseMensajeDto;

@Service
public class BancoService implements IBancoService {

	private final IBancoDao bancoDao;
	final MapperBanco mapperBanco = new MapperBanco();

	public BancoService(IBancoDao bancoDao) {
		this.bancoDao = bancoDao;
	}

	public Banco mostrarBancos(Integer idBanco) {
		return bancoDao.getOne(idBanco);
	}

	@Override
	public List<ResponseBancoDto> listarBancos() {
		List<ResponseBancoDto> bancos = new LinkedList<>();
		List<Banco> bancoEntities = bancoDao.findAll();
		for (Banco banco : bancoEntities) {
			bancos.add(mapperBanco.listarBancos(banco));
		}
		return bancos;
	}

	@Override
	public ResponseMensajeDto actualizarBancos(RequestBancoDto requestBanco) {
		ValidacionesActualizarServicios validaBanco = new ValidacionesActualizarServicios();
		Optional<Banco> dataBanco = bancoDao.findById(requestBanco.getId_banco());
		ResponseMensajeDto mensajeDto = new ResponseMensajeDto();
		Banco datosModificar = dataBanco.get();
		validaBanco.validarBanco(datosModificar, requestBanco);
		mensajeDto.setCodigoRespuesta(Constantes.COD_RESPUESTA_ACTUALIZAR);
		mensajeDto.setMensajeRespuesta(Constantes.MENSAJE_DE_ACTUALIZACION_EXITOSA);
		bancoDao.save(datosModificar);
		return mensajeDto;
	}

}
