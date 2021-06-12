package com.bank.runner.application.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.bank.runner.application.dao.IBancoDao;
import com.bank.runner.application.dao.ICuentaDao;
import com.bank.runner.application.dao.IMovimientosDao;
import com.bank.runner.application.dao.ITipoMoviemientosDao;
import com.bank.runner.application.dto.RequestMovimientosDto;
import com.bank.runner.application.dto.ResponseMensajeDto;
import com.bank.runner.application.dto.ResponseMovimientosDto;
import com.bank.runner.application.entities.Banco;
import com.bank.runner.application.entities.Cuenta;
import com.bank.runner.application.entities.Movimiento;
import com.bank.runner.application.entities.TipoMovimiento;
import com.bank.runner.application.models.BancoModel;
import com.bank.runner.application.models.CuentaModel;
import com.bank.runner.application.models.TipoMovimientosModel;
import com.bank.runner.application.service.IMovimientosService;
import com.bank.runner.application.util.Constantes;
import com.bank.runner.application.util.Validaciones;
import com.bank.runner.application.util.ValidacionesActualizarServicios;

public class MovimientosServiceTest {

	Date fecha = new Date();

	private IMovimientosDao iMovimientosDao;
	private ITipoMoviemientosDao iTipoMoviemientosDao;
	private ICuentaDao iCuentaDao;
	private IBancoDao iBancoDao;
	private Validaciones validaciones;
	private ValidacionesActualizarServicios validacionesActualizarServicios;

	@Before
	public void setUp() {
		iMovimientosDao = Mockito.mock(IMovimientosDao.class);
		iTipoMoviemientosDao = Mockito.mock(ITipoMoviemientosDao.class);
		iCuentaDao = Mockito.mock(ICuentaDao.class);
		iBancoDao = Mockito.mock(IBancoDao.class);
		validaciones = new Validaciones();
		validacionesActualizarServicios = new ValidacionesActualizarServicios();
	}

	@Test(expected = RuntimeException.class)
	public void CuandoRecibeValoresNulosDebeRetornarUnaExcepcion() {
		IMovimientosService movientoServicio = new MovimientosService(iMovimientosDao, iCuentaDao, null, iTipoMoviemientosDao,
				iBancoDao, validaciones);
		movientoServicio.mostrarMovimiento(null);
	}

	@Test
	public void CuandoLlamamosMostrarMovimientoDebeRetornarUnMovimiento() {
		IMovimientosService movimientosService = new MovimientosService(iMovimientosDao, iCuentaDao,
				null, iTipoMoviemientosDao, iBancoDao, validaciones);
		Movimiento movimientoPrueba = new Movimiento();
		movimientoPrueba.setIdMovimientos(2);
		movimientoPrueba.setBanco(new Banco());
		movimientoPrueba.setCuenta(new Cuenta());
		movimientoPrueba.setTipoMovimiento(new TipoMovimiento());

		Mockito.when(iMovimientosDao.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(movimientoPrueba));

		ResponseMovimientosDto responseMovimientosDto = movimientosService.mostrarMovimiento(5);

		Assert.assertNotNull(responseMovimientosDto);
		Assert.assertEquals(movimientoPrueba.getIdMovimientos(), responseMovimientosDto.getIdMovimientos());
	}

	@Test(expected = RuntimeException.class)
	public void CuandoSeRegistreYElRequestVengaVacioRetornaUnaExcepcion() {
		RequestMovimientosDto requestMovimientosDto = new RequestMovimientosDto();
		IMovimientosService movimientosService = new MovimientosService(iMovimientosDao, iCuentaDao,
				null, iTipoMoviemientosDao, iBancoDao, validaciones);
		movimientosService.registrarMovimiento(requestMovimientosDto);
	}

	@Test
	public void CuandoSeRegistreYRequestVengaBienDebeRetornarUnResponseMensajeDto() {
		IMovimientosService movimientosService = new MovimientosService(iMovimientosDao, iCuentaDao,
				null, iTipoMoviemientosDao, iBancoDao, validaciones);
		Movimiento movimiento = new Movimiento();
		Banco banco = new Banco();
		Cuenta cuenta = new Cuenta();
		TipoMovimiento tipoMovimiento = new TipoMovimiento();

		movimiento.setIdMovimientos(1);
		movimiento.setBanco(new Banco());
		movimiento.setCuenta(new Cuenta());
		movimiento.setTipoMovimiento(new TipoMovimiento());

		RequestMovimientosDto requestMovimientosDto = new RequestMovimientosDto();

		BancoModel bancoModel = new BancoModel();
		CuentaModel cuentaModel = new CuentaModel();
		TipoMovimientosModel tipoMovimientosModel = new TipoMovimientosModel();

		bancoModel.setIdBanco(1);
		cuentaModel.setId(2);
		tipoMovimientosModel.setIdTipoMovimientos(4);

		requestMovimientosDto.setIdMovimientos(2);
		requestMovimientosDto.setBanco(bancoModel);
		requestMovimientosDto.setCuenta(cuentaModel);
		requestMovimientosDto.setTipoMovimiento(tipoMovimientosModel);
		requestMovimientosDto.setCuentaDestinatario("23432");
		requestMovimientosDto.setFecha(fecha);
		requestMovimientosDto.setNombreEmpresa("Intergrupo");
		requestMovimientosDto.setValor(new BigDecimal(10000));

		Mockito.when(iMovimientosDao.save(movimiento)).thenReturn(movimiento);
		Mockito.when(iBancoDao.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(banco));
		Mockito.when(iCuentaDao.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(cuenta));
		Mockito.when(iTipoMoviemientosDao.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(tipoMovimiento));

		ResponseMensajeDto mensajeAComparar = new ResponseMensajeDto();
		mensajeAComparar.setCodigoRespuesta(Constantes.COD_RESPUESTA_REGISTRO);
		mensajeAComparar.setMensajeRespuesta(Constantes.MENSAJE_REGISTRAR);

		ResponseMensajeDto responseMensajeDto = movimientosService.registrarMovimiento(requestMovimientosDto);

		Assert.assertNotNull(responseMensajeDto);
		Assert.assertEquals(mensajeAComparar.getCodigoRespuesta(), responseMensajeDto.getCodigoRespuesta());
	}

}
