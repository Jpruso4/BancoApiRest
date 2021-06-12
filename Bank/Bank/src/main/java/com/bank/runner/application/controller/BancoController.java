package com.bank.runner.application.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bank.runner.application.dto.RequestBancoDto;
import com.bank.runner.application.dto.ResponseBancoDto;
import com.bank.runner.application.dto.ResponseMensajeDto;
import com.bank.runner.application.mapper.impl.MapperBanco;
import com.bank.runner.application.service.impl.BancoService;
import com.bank.runner.application.util.Constantes;

@RestController
@RequestMapping(Constantes.BANCO_CONTROLLER)
public class BancoController {

	private final BancoService bancoService;
	private final MapperBanco mapperBanco;

	public BancoController(BancoService bancoService, MapperBanco mapperBanco) {
		this.bancoService = bancoService;
		this.mapperBanco = mapperBanco;
	}

	@GetMapping(value = "/{idBanco}")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseBancoDto mostrarBanco(@PathVariable("idBanco") Integer idBanco) {
		return mapperBanco.listarBancos(bancoService.mostrarBancos(idBanco));
	}
	
	@GetMapping(value = "")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public List<ResponseBancoDto> listarBancos (){
		return bancoService.listarBancos();
		
	}
	
	@PutMapping(produces = "application/json", consumes = "application/json")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseMensajeDto actualizarBancos(@RequestBody RequestBancoDto requestBanco) {
		return bancoService.actualizarBancos(requestBanco);
		
	}
}
