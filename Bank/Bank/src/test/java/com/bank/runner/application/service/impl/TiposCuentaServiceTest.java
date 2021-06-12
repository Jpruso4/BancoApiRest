package com.bank.runner.application.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import com.bank.runner.application.dao.IClienteDao;
import com.bank.runner.application.dao.ITiposCuentaDao;
import com.bank.runner.application.entities.Cliente;
import com.bank.runner.application.entities.TiposCuenta;
import com.bank.runner.application.service.IClienteService;
import com.bank.runner.application.service.ITiposCuentaService;
import com.bank.runner.application.util.Validaciones;

public class TiposCuentaServiceTest {
    private ITiposCuentaDao iTiposCuentaDao;
	
	@Before
	public void setUp() {
		iTiposCuentaDao = Mockito.mock(ITiposCuentaDao.class);
	}
	
	@Test(expected = RuntimeException.class)
	public void cuandoTiposCuentaServiceRecibeValorNuloEsteRetornaExcepcion() {
		ITiposCuentaService tiposCuentaService = new TiposCuentaService(iTiposCuentaDao);
		tiposCuentaService.mostrarTipoCuenta(null);
	}
	@Test
	public void cuandoTipoCuentaServiceRecibeValorUnValidoDeberaRetornarUnTipoCuenta() {
			
			TiposCuenta tiposCuenta = new TiposCuenta();
			tiposCuenta.setIdTiposCuenta(6);
			tiposCuenta.setDescripcion("Disponible para funcionamiento");
			tiposCuenta.setEstado(1);
			tiposCuenta.setTipoCuenta("Cuenta de nomina");

			Mockito.when(iTiposCuentaDao.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(tiposCuenta));
			
			Assert.assertNotNull(tiposCuenta);
		}

}
