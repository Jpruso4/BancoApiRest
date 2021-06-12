package com.bank.runner.application.service;

import com.bank.runner.application.dto.ResponseMensajeDto;
import com.bank.runner.application.models.FacturaModel;

public interface IPagoService {
	
	public FacturaModel consultarFactura(int referencia);

	public ResponseMensajeDto registrarPago(FacturaModel datosPagoNuevo);
}
