package com.bank.runner.application.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bank.runner.application.dto.RequestClienteDto;
import com.bank.runner.application.dto.ResponseClienteDto;
import com.bank.runner.application.dto.ResponseMensajeDto;
import com.bank.runner.application.service.impl.ClienteService;
import com.bank.runner.application.util.Constantes;

@RestController
@RequestMapping(Constantes.CLIENTE_CONTROLLER)
public class ClienteController {

	private final ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@GetMapping("/export")
	@CrossOrigin
 	public ResponseEntity<String> exportToCsvSimpleString(HttpServletResponse response)throws IOException {
		final String csvData = clienteService.MostrarCsvDeClientes(response);
		
		HttpHeaders headers = new HttpHeaders();
		
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.TEXT_PLAIN)
				.body(csvData);
	}

	@GetMapping(value = "/{idCliente}")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseClienteDto mostrarCliente(@PathVariable("idCliente") Integer idCliente) {
		return clienteService.mostrarCliente(idCliente);
	}

	@GetMapping(value = "")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public List<ResponseClienteDto> mostrarListaClientes() {
		return clienteService.mostrarListaClientes();
	}

	@PostMapping(value = "", produces = "application/json", consumes = "application/json")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseMensajeDto registrarCliente(@RequestBody RequestClienteDto datosClienteNuevo) {
		return clienteService.registrarCliente(datosClienteNuevo);
	}

	@PatchMapping(value = "/{idCliente}")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseMensajeDto estadoCliente(@PathVariable("idCliente") Integer idCliente) {
		return clienteService.actualizarEstadoCliente(idCliente);
	}

	@PutMapping(produces = "application/json", consumes = "application/json")
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseMensajeDto actualizarCliente(@RequestBody RequestClienteDto requestCliente) {
		return clienteService.actualizarDatos(requestCliente);
	}

}

