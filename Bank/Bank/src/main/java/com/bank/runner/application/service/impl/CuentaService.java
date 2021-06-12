package com.bank.runner.application.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.runner.application.dao.IBancoDao;
import com.bank.runner.application.dao.IClienteDao;
import com.bank.runner.application.dao.ICliente_cuenta;
import com.bank.runner.application.dao.ICuentaDao;
import com.bank.runner.application.dao.ITiposCuentaDao;
import com.bank.runner.application.dto.RequestCuentaDto;
import com.bank.runner.application.dto.ResponseCuentaDto;
import com.bank.runner.application.dto.ResponseMensajeDto;
import com.bank.runner.application.entities.Banco;
import com.bank.runner.application.entities.Cliente;
import com.bank.runner.application.entities.ClienteCuenta;
import com.bank.runner.application.entities.Cuenta;
import com.bank.runner.application.entities.TiposCuenta;
import com.bank.runner.application.mapper.impl.MapperCuenta;
import com.bank.runner.application.models.FacturaModel;
import com.bank.runner.application.service.ICuentaService;
import com.bank.runner.application.util.Constantes;
import com.bank.runner.application.util.Validaciones;
import com.bank.runner.application.util.ValidacionesActualizarServicios;

@Service
public class CuentaService implements ICuentaService {
	private final ICuentaDao cuentaDao;
	private final ITiposCuentaDao tiposCuentaDao;
	private final IBancoDao bancoDao;
	private final ICliente_cuenta clienteCuantaDao;
	private final IClienteDao clienteDao;
	private final Validaciones validacion;
	
	@Autowired
	public CuentaService(ICuentaDao cuentaDao, ITiposCuentaDao tiposCuentaDao, IBancoDao bancoDao,
			ICliente_cuenta clienteCuantaDao, IClienteDao clienteDao, Validaciones validacion) {
		this.cuentaDao = cuentaDao;
		this.tiposCuentaDao = tiposCuentaDao;
		this.bancoDao = bancoDao;
		this.clienteCuantaDao = clienteCuantaDao;
		this.clienteDao = clienteDao;
		this.validacion = validacion;
	}

	java.util.Date fecha = new Date();

	final MapperCuenta mapper = new MapperCuenta();

	@Override
	public ResponseCuentaDto mostrarCuenta(Integer idCuenta) {
		Cuenta cuentas = cuentaDao.obtenerCuenta(idCuenta);
		ResponseCuentaDto responseCuenta = mapper.mostrarCuenta(cuentas);
		return responseCuenta;
	}

	@Override
	public List<ResponseCuentaDto> mostrarListaCuenta() {
		List<ResponseCuentaDto> cuentas = new LinkedList<>();
		List<Cuenta> cuentaEntities = cuentaDao.findAll();
		for (Cuenta cuenta : cuentaEntities) {
			cuentas.add(mapper.mostrarCuenta(cuenta));
		}

		return cuentas;
	}

	@Override
	public ResponseMensajeDto registrarCuenta(RequestCuentaDto datosCuentaNueva) {
		ClienteCuenta registroCuentaCliente = new ClienteCuenta();
		Cliente clienteCuenta = new Cliente();
		ResponseMensajeDto respuestaPeticion = new ResponseMensajeDto();
		Cuenta registroCuenta = new Cuenta();
		Optional<Banco> bancoEntity = validacion.validarBanco(datosCuentaNueva, bancoDao);
		Optional<TiposCuenta> tiposCuentaEntity = validacion.validarTipoCuenta(datosCuentaNueva, tiposCuentaDao);
		Optional<Cliente> cliente = validacion.validarClienteCuenta(datosCuentaNueva, clienteDao);
		registroCuenta.setBanco(bancoEntity.get());
		registroCuenta.setTiposCuenta(tiposCuentaEntity.get());
		clienteCuenta = cliente.get();
		registroCuenta.setIdCuenta(datosCuentaNueva.getIdCuenta());
		registroCuenta.setNumeroCuenta(generarNumeroCuenta());
		registroCuenta.setFechaCreacion(fecha);
		registroCuenta.setSaldo(datosCuentaNueva.getSaldo());
		registroCuenta.setEstado(1);
		cuentaDao.save(registroCuenta);
		registroCuentaCliente.setCliente(clienteCuenta);
		registroCuentaCliente.setCuenta(cuentaDao.obtenerIdCuenta(registroCuenta.getNumeroCuenta()));
		clienteCuantaDao.save(registroCuentaCliente);
		respuestaPeticion.setCodigoRespuesta(Constantes.COD_RESPUESTA_REGISTRO);
		respuestaPeticion.setMensajeRespuesta(Constantes.MENSAJE_REGISTRAR);
		return respuestaPeticion;
	}

	@Override
	public ResponseMensajeDto actualizarEstadoCuenta(Integer idCuenta) {
		ResponseMensajeDto respuestaPeticion = new ResponseMensajeDto();
		Cuenta cuenta = new Cuenta();
		Optional<Cuenta> cuentaEstado = validacion.validarEstadoCuenta(idCuenta, cuentaDao);
		cuenta = cuentaEstado.get();
		cambiarEstadoCuenta(cuenta);
		cuentaDao.save(cuenta);
		respuestaPeticion.setCodigoRespuesta(Constantes.COD_RESPUESTA_ACTUALIZAR);
		respuestaPeticion.setMensajeRespuesta(Constantes.MENSAJE_DE_ACTUALIZACION_ESTADO_EXITOSA);
		return respuestaPeticion;
	}

	@Override
	public ResponseMensajeDto actualizarDatosCuenta(RequestCuentaDto requestCuenta) {
		ValidacionesActualizarServicios validacionActualizarCuenta = new ValidacionesActualizarServicios();
		ResponseMensajeDto respuestaMensaje = new ResponseMensajeDto();
		Optional<Cuenta> cuentaData = cuentaDao.findById(requestCuenta.getIdCuenta());
		Cuenta datosModificar = cuentaData.get();
		datosModificar.getIdCuenta();
		validacionActualizarCuenta.validarCuenta(requestCuenta, datosModificar, cuentaData, bancoDao, tiposCuentaDao);
		cuentaDao.save(datosModificar);
		respuestaMensaje.setCodigoRespuesta(Constantes.COD_RESPUESTA_ACTUALIZAR);
		respuestaMensaje.setMensajeRespuesta(Constantes.MENSAJE_DE_ACTUALIZACION_EXITOSA);
		return respuestaMensaje;
	}

	private Cuenta descontarSaldoCuenta(Cuenta cuenta, FacturaModel datosPagoNuevo) {
		cuenta.setSaldo(cuenta.getSaldo().subtract(BigDecimal.valueOf(datosPagoNuevo.getTotal_fact())));
		return cuenta;
	}
	
	private String generarNumeroCuenta() {
		String numeroCuenta = null;
		Random random = new Random();
		int randomInt = random.nextInt();
		do {
			randomInt = Math.abs(randomInt + random.nextInt());
			numeroCuenta = Integer.toString(randomInt);
		} while (numeroCuenta.length() > 10 || numeroCuenta.length() < 10);
		return numeroCuenta;
	}

	private Cuenta cambiarEstadoCuenta(Cuenta cuenta) {
		if (cuenta.getEstado() == 1) {
			cuenta.setEstado(Constantes.ESTADO_INACTIVO);
		} else {
			cuenta.setEstado(Constantes.ESTADO_ACTIVO);
		}
		return cuenta;
	}

	@Transactional
	public ResponseMensajeDto modificarSaldoCuenta(FacturaModel datosPagoNuevo) {
		ResponseMensajeDto respuestaPeticion = new ResponseMensajeDto();
		Cuenta cuenta = new Cuenta();
		Optional<Cuenta> cuentaEntity = validacion.validarExisteCuenta(datosPagoNuevo.getNumeroCuenta(), cuentaDao);
		cuenta = cuentaEntity.get();
		descontarSaldoCuenta(cuenta, datosPagoNuevo);
		cuentaDao.save(cuenta);
		respuestaPeticion.setCodigoRespuesta(Constantes.COD_RESPUESTA_ACTUALIZAR);
		respuestaPeticion.setMensajeRespuesta(Constantes.MENSAJE_DE_ACTUALIZACION_EXITOSA);
		return respuestaPeticion;
	}

}