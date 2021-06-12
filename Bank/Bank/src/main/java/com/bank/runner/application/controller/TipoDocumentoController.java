package com.bank.runner.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bank.runner.application.dto.RequestTipoDocumentoDto;
import com.bank.runner.application.dto.ResponseMensajeDto;
import com.bank.runner.application.dto.ResponseTipoDocumentoDto;
import com.bank.runner.application.mapper.impl.MapperTipoDocumento;
import com.bank.runner.application.service.impl.TipoDocumentoService;
import com.bank.runner.application.util.Constantes;

@RestController
@RequestMapping(Constantes.TIPODOCUMENTO_CONTROLLER)
public class TipoDocumentoController {
	
	private final TipoDocumentoService tipoDocumentoService;

	@Autowired
	public TipoDocumentoController(TipoDocumentoService tipoDocumentoService, MapperTipoDocumento mapperTipoDocumento) {
		this.tipoDocumentoService = tipoDocumentoService;
	}

	@GetMapping(value = "/{idTipoDocumento}")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseTipoDocumentoDto mostrarTipoDocumento(@PathVariable("idTipoDocumento") Integer idTipoDocumento) {
		return tipoDocumentoService.mostrarTipoDocumento(idTipoDocumento);
	}	

	@GetMapping(value = "")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public List<ResponseTipoDocumentoDto> mostrarListaTiposDocumentos() {
		return tipoDocumentoService.mostrarListaTiposDocumentos();
	}

	@PostMapping(value = "", produces = "application/json", consumes = "application/json")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseMensajeDto registrarTipoDocumento(@RequestBody ResponseTipoDocumentoDto tipoDocumentoNuevo) {
		return tipoDocumentoService.registrarTipoDocumento(tipoDocumentoNuevo);
	}
	@PutMapping(produces = "application/json", consumes = "application/json")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseMensajeDto actualizarTipoDocumento(@RequestBody RequestTipoDocumentoDto requestTipoDocumento) {
		return tipoDocumentoService.actualizarTipoDocumento(requestTipoDocumento);
	}
	@DeleteMapping(value = "/{idTipoDocumento}")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseMensajeDto eliminarTipoDocumento(@PathVariable("idTipoDocumento") Integer idTipoDocumento) {
		return tipoDocumentoService.eliminarTipoDocumento(idTipoDocumento);
	}
}
