package com.bank.runner.application.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.bank.runner.application.dao.ITipoMoviemientosDao;
import com.bank.runner.application.service.ITipoMovimientosService;

public class TipoMovimientosServiceTest {

	private ITipoMoviemientosDao iTipoMoviemientosDao;
	
	@Before
	public void setUp() {
		iTipoMoviemientosDao = Mockito.mock(ITipoMoviemientosDao.class);
	}
	
	@Test(expected = RuntimeException.class)
	public void CuandoMostrarTipoMovimientoRecibeNuloDebeRetornarUnaExcepcion() {
		ITipoMovimientosService movimientosService = new TipoMovimientosService(iTipoMoviemientosDao);
		movimientosService.mostrarTipoMovimientos(null);
	}
	
	@Test
	public void CuandoLlamamosMostrarTipoMovimientosDebeRetornarUnTipoDeMovimiento() {
		
	}
}
