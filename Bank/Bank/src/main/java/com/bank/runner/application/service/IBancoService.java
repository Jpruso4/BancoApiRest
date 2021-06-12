package com.bank.runner.application.service;

import java.util.List;

import com.bank.runner.application.dto.RequestBancoDto;
import com.bank.runner.application.dto.ResponseBancoDto;
import com.bank.runner.application.dto.ResponseMensajeDto;
import com.bank.runner.application.entities.Banco;

public interface IBancoService  {
	
	public Banco mostrarBancos (Integer idBanco);
	
	public List<ResponseBancoDto> listarBancos();
	
	public ResponseMensajeDto actualizarBancos (RequestBancoDto requestBanco);

}
