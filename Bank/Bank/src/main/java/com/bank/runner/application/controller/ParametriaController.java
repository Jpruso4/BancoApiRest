package com.bank.runner.application.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bank.runner.application.models.ParametriaModel;
import com.bank.runner.application.service.IParametriaService;

@RestController
@RequestMapping(path = "/urls")
public class ParametriaController {
	
	private final IParametriaService parametriaService;

	@Autowired
	public ParametriaController(IParametriaService parametriaService) {
		this.parametriaService = parametriaService;
	}
	
	@GetMapping
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public List<ParametriaModel> consultarFactura() {
		return parametriaService.consultarParametria();
	}
	
}
