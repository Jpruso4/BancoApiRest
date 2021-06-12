package com.bank.runner.application.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.bank.runner.application.dao.IBancoDao;
import com.bank.runner.application.dao.IClienteDao;
import com.bank.runner.application.dao.ICliente_cuenta;
import com.bank.runner.application.dao.ICuentaDao;
import com.bank.runner.application.dao.ITiposCuentaDao;
import com.bank.runner.application.dto.ResponseCuentaDto;
import com.bank.runner.application.entities.Banco;
import com.bank.runner.application.entities.Cuenta;
import com.bank.runner.application.entities.TiposCuenta;
import com.bank.runner.application.service.ICuentaService;
import com.bank.runner.application.util.Validaciones;

public class CuentaServiceTest {

	private ICuentaDao iCuentaDao;
	private ICliente_cuenta iClienteCuentaDao;
	private IClienteDao iClienteDao;
	private ITiposCuentaDao iTiposCuentaDao;
	private IBancoDao iBancoDao;
	private Validaciones validaciones;

	@Before
	public void setUp() {
		iCuentaDao = Mockito.mock(ICuentaDao.class);
		iTiposCuentaDao = Mockito.mock(ITiposCuentaDao.class);
		iBancoDao = Mockito.mock(IBancoDao.class);
		iClienteCuentaDao = Mockito.mock(ICliente_cuenta.class);
		iClienteDao = Mockito.mock(IClienteDao.class);
		validaciones = new Validaciones();
	}

	@Test(expected = RuntimeException.class)
	public void cuandoServicioCuentaRecibeValoresNulos() {
		ICuentaService cuentaService = new CuentaService(iCuentaDao,iTiposCuentaDao,iBancoDao, iClienteCuentaDao ,iClienteDao,validaciones);
		cuentaService.mostrarCuenta(null);
	}
	@Test
	public void cuandoSeLLamaMostracCuentaYElIdDeLaCuentaEsValidoDebeRetornarCuenta() {
		ICuentaService cuentaService = new CuentaService(iCuentaDao,iTiposCuentaDao,iBancoDao, iClienteCuentaDao ,iClienteDao,validaciones);
		Cuenta cuentaPrueba = new Cuenta();
//		Cliente clientePrueba = new Cliente();
//		clientePrueba.setIdCliente(2);
//		clientePrueba.setTipoDocumento(new TipoDocumento());
		cuentaPrueba.setIdCuenta(2);
        cuentaPrueba.setNumeroCuenta("456737446");
		cuentaPrueba.setFechaCreacion(new Date(2021-02-26));
		cuentaPrueba.setTiposCuenta(new TiposCuenta());
		cuentaPrueba.setSaldo(new BigDecimal(24252.98));
		cuentaPrueba.setEstado(1);
		cuentaPrueba.setBanco(new Banco());
		//cuentaPrueba.setCuentaMovimientos(new cuentaMovimientos());

		Mockito.when(iCuentaDao.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(cuentaPrueba));
		
		ResponseCuentaDto responseCuentaDto = cuentaService.mostrarCuenta(2);
		
		Assert.assertNotNull(responseCuentaDto);
		Assert.assertEquals(responseCuentaDto.getIdCuenta(), cuentaPrueba.getIdCuenta());

	}

}
