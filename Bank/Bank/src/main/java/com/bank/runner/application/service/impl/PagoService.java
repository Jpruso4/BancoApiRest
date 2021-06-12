package com.bank.runner.application.service.impl;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.bank.runner.application.dto.ResponseMensajeDto;
import com.bank.runner.application.models.FacturaModel;
import com.bank.runner.application.service.IPagoService;
import com.bank.runner.application.util.Constantes;

@Service
public class PagoService implements IPagoService{
	
	private final CuentaService cuentaService;
	private final RestTemplate restTemplate;
	

	@Autowired
	public PagoService(CuentaService cuentaService, RestTemplate restTemplate) {
		this.cuentaService = cuentaService;
		this.restTemplate = restTemplate;
	}

	@Override
	public FacturaModel consultarFactura(int referencia) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);
		FacturaModel facturaModel = null;
		try {
			 facturaModel = restTemplate.exchange("http://localhost:8888/pagos/template/factura/" + referencia, HttpMethod.GET, entity, FacturaModel.class).getBody();
		}catch(HttpClientErrorException e) {
			System.out.println(e.getMessage());
		}
		return facturaModel;
	}
	
	@Override
	public ResponseMensajeDto registrarPago(FacturaModel datosPagoNuevo) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<FacturaModel> entity = new HttpEntity<>(datosPagoNuevo,headers);
	    ResponseEntity<String> respuestaPagoEfectudo = restTemplate.postForEntity("http://localhost:8888/pagos", entity, String.class);	
	 
	    if(respuestaPagoEfectudo.getStatusCode()== HttpStatus.CREATED) {
	    	return cuentaService.modificarSaldoCuenta(datosPagoNuevo);
	    }else {
	    	throw new NoSuchElementException(Constantes.PAGO_NO_EFECTUADO);
	    }
	 }
}
