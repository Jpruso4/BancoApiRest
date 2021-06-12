package com.bank.runner.application.service;

import java.util.List;

import com.bank.runner.application.entities.TiposCuenta;

public interface ITiposCuentaService {
	
	public TiposCuenta mostrarTipoCuenta (Integer idTipoCuenta);
	
	List<TiposCuenta> mostrarTiposaCuenta ();

}
