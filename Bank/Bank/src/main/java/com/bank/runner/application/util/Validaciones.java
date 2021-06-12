package com.bank.runner.application.util;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;

import com.bank.runner.application.dao.IBancoDao;
import com.bank.runner.application.dao.IClienteDao;
import com.bank.runner.application.dao.ICuentaDao;
import com.bank.runner.application.dao.IMovimientosDao;
import com.bank.runner.application.dao.ITipoDocumentoDao;
import com.bank.runner.application.dao.ITipoMoviemientosDao;
import com.bank.runner.application.dao.ITiposCuentaDao;
import com.bank.runner.application.dto.RequestClienteDto;
import com.bank.runner.application.dto.RequestCuentaDto;
import com.bank.runner.application.dto.RequestMovimientosDto;
import com.bank.runner.application.entities.Banco;
import com.bank.runner.application.entities.Cliente;
import com.bank.runner.application.entities.Cuenta;
import com.bank.runner.application.entities.Movimiento;
import com.bank.runner.application.entities.TipoDocumento;
import com.bank.runner.application.entities.TipoMovimiento;
import com.bank.runner.application.entities.TiposCuenta;

public class Validaciones {

	public Optional<TiposCuenta> validarExisteTipoCuenta(RequestCuentaDto datosCuentaNueva,
			ITiposCuentaDao tiposCuentaDao) {
		Optional<TiposCuenta> tiposCuentaEntity = tiposCuentaDao
				.findById(datosCuentaNueva.getTiposCuenta().getIdTiposCuenta());
		if (!tiposCuentaEntity.isPresent()) {
			throw new NoSuchElementException(Constantes.IDTIPOCUENTA_INEXISTENTE);
		} else
			return tiposCuentaEntity;
	}

	public Optional<Banco> validarExisteBanco(RequestMovimientosDto requestMovimientosDto, IBancoDao bancoDao) {
		Optional<Banco> bancoEntity = bancoDao.findById(requestMovimientosDto.getBanco().getIdBanco());
		if (!bancoEntity.isPresent()) {
			throw new NoSuchElementException(Constantes.IDBANCO_INEXISTENTE);
		} else
			return bancoEntity;
	}

	public Optional<Cuenta> validarExisteCuenta(RequestMovimientosDto requestMovimientosDto, ICuentaDao cuentaDao) {
		Optional<Cuenta> cuentaEntity = cuentaDao.findById(requestMovimientosDto.getCuenta().getId());
		if (!cuentaEntity.isPresent()) {
			throw new NoSuchElementException(Constantes.NUMERO_DE_CUENTA_INEXISTENTE);
		} else
			return cuentaEntity;
	}
	
	public Optional<Cuenta> validarExisteCuenta(String numeroCuenta, ICuentaDao cuentaDao){
		Optional<Cuenta> cuentaEntity = cuentaDao.obtenerCuentaNumeroCuenta(numeroCuenta);
		if(!cuentaEntity.isPresent()) {
			throw new NoSuchElementException(Constantes.NUMERO_DE_CUENTA_INEXISTENTE);
		}else
			return cuentaEntity;
	}

	public Optional<TipoMovimiento> validarExisteTipoMovimiento(RequestMovimientosDto requestMovimientosDto,
			ITipoMoviemientosDao tipoMoviemientosDao) {
		Optional<TipoMovimiento> tipoMovimientoEntity = tipoMoviemientosDao
				.findById(requestMovimientosDto.getTipoMovimiento().getIdTipoMovimientos());
		if (!tipoMovimientoEntity.isPresent()) {
			throw new NoSuchElementException(Constantes.TIPO_DE_MOVIMIENTO_INEXISTENTE);
		} else
			return tipoMovimientoEntity;
	}

	public Optional<Movimiento> validarExisteMovimiento(RequestMovimientosDto requestMovimiento,
			IMovimientosDao movimientosDao) {
		Optional<Movimiento> movimientoData = movimientosDao.findById(requestMovimiento.getIdMovimientos());
		if (!movimientoData.isPresent()) {
			throw new NoSuchElementException(Constantes.MENSAJE_NULO);
		}
		return movimientoData;
	}

	public Optional<Banco> validarBanco(RequestCuentaDto datosCuentaNueva, IBancoDao bancoDao) {
		Optional<Banco> bancoEntity = bancoDao.findById(datosCuentaNueva.getBanco().getIdBanco());
		if (!bancoEntity.isPresent()) {
			throw new NoSuchElementException(Constantes.IDBANCO_INEXISTENTE);
		}
		return bancoEntity;

	}

	public Optional<TiposCuenta> validarTipoCuenta(RequestCuentaDto datosCuentaNueva, ITiposCuentaDao tiposCuentaDao) {
		Optional<TiposCuenta> tiposCuentaEntity = tiposCuentaDao
				.findById(datosCuentaNueva.getTiposCuenta().getIdTiposCuenta());
		if (!tiposCuentaEntity.isPresent()) {
			throw new NoSuchElementException(Constantes.IDTIPOCUENTA_INEXISTENTE);
		}
		return tiposCuentaEntity;
	}

	public Optional<Cliente> validarClienteCuenta(RequestCuentaDto datosCuentaNueva, IClienteDao clienteDao) {
		Optional<Cliente> cliente = clienteDao.obtenerCliente(datosCuentaNueva.getCliente().getNumeroDocumento());
		if (!cliente.isPresent()) {
			throw new NoSuchElementException(Constantes.CLIENTE_INEXISTENTE);
		}
		return cliente;
	}

	public Optional<Cliente> validarCliente(Integer idCliente, IClienteDao clienteDao) {
		Optional<Cliente> cliente = clienteDao.findById(idCliente);
		if (!cliente.isPresent()) {
			throw new NoSuchElementException(Constantes.MENSAJE_NULO);
		}
		return cliente;
	}

	public Optional<Cliente> validarDuplicidadCliente(RequestClienteDto datosClienteNuevo, IClienteDao clienteDao) {
		Optional<Cliente> clienteEstado = clienteDao.findById(datosClienteNuevo.getIdCliente());
		if (clienteEstado.isPresent()) {
			throw new DuplicateKeyException(Constantes.IDClIENTE_DUPLICADO);
		}
		return clienteEstado;
	}

	public Optional<TipoDocumento> validarTipoDocumento(RequestClienteDto datosClienteNuevo,
			ITipoDocumentoDao tipodocumentoDao) {
		Optional<TipoDocumento> tipoDocumento = tipodocumentoDao
				.findById(datosClienteNuevo.getTipoDocumento().getIdTipoDocumento());
		if (!tipoDocumento.isPresent()) {
			throw new NoSuchElementException(Constantes.TIPO_DOCUMENTO_INEXISTENTE);
		}
		return tipoDocumento;
	}

	public Optional<Cuenta> validarEstadoCuenta(Integer idCuenta, ICuentaDao cuentaDao) {
		Optional<Cuenta> cuentaEstado = cuentaDao.findById(idCuenta);
		if (!cuentaEstado.isPresent()) {
			throw new NoSuchElementException(Constantes.MENSAJE_NULO);
		}
		return cuentaEstado;
	}
}
