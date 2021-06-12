package com.bank.runner.application.util;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;

import com.bank.runner.application.dao.IBancoDao;
import com.bank.runner.application.dao.ICuentaDao;
import com.bank.runner.application.dao.IMovimientosDao;
import com.bank.runner.application.dao.ITipoDocumentoDao;
import com.bank.runner.application.dao.ITipoMoviemientosDao;
import com.bank.runner.application.dao.ITiposCuentaDao;
import com.bank.runner.application.dto.RequestBancoDto;
import com.bank.runner.application.dto.RequestClienteDto;
import com.bank.runner.application.dto.RequestCuentaDto;
import com.bank.runner.application.dto.RequestMovimientosDto;
import com.bank.runner.application.dto.RequestTipoDocumentoDto;
import com.bank.runner.application.dto.RequestTipoMovimientosDto;
import com.bank.runner.application.entities.Banco;
import com.bank.runner.application.entities.Cliente;
import com.bank.runner.application.entities.Cuenta;
import com.bank.runner.application.entities.Movimiento;
import com.bank.runner.application.entities.TipoDocumento;
import com.bank.runner.application.entities.TipoMovimiento;
import com.bank.runner.application.entities.TiposCuenta;

public class ValidacionesActualizarServicios {
	public Cliente validarCliente(Cliente datosModificar, RequestClienteDto requestCliente,ITipoDocumentoDao tipodocumentoDao) {
		Validaciones validaciones = new Validaciones();
		if (!(requestCliente.getTipoDocumento()
				.getIdTipoDocumento() == (datosModificar.getTipoDocumento().getIdTipoDocumento()))) {
			Optional<TipoDocumento> tipoDocumentoCliente = validaciones.validarTipoDocumento(requestCliente, tipodocumentoDao);
			datosModificar.setTipoDocumento(tipoDocumentoCliente.get());
		}
		if (!(requestCliente.getPrimerNombre().equals(datosModificar.getPrimerNombre()))) {
			datosModificar.setPrimerNombre(requestCliente.getPrimerNombre());
		}
		if (!requestCliente.getSegundoNombre().equals(datosModificar.getSegundoNombre())) {
			datosModificar.setSegundoNombre(requestCliente.getSegundoNombre());
		}
		if (!requestCliente.getPrimerApellido().equals(datosModificar.getPrimerApellido())) {
			datosModificar.setPrimerApellido(requestCliente.getPrimerApellido());
		}
		if (!requestCliente.getSegundoApellido().equals(datosModificar.getSegundoApellido())) {
			datosModificar.setSegundoApellido(requestCliente.getSegundoApellido());
		}
		if (!requestCliente.getDireccion().equals(datosModificar.getDireccion())) {
			datosModificar.setDireccion(requestCliente.getDireccion());
		}
		if (!requestCliente.getTelefono().equals(datosModificar.getTelefono())) {
			datosModificar.setTelefono(requestCliente.getTelefono());
		}
		if (!requestCliente.getCelular().equals(datosModificar.getCelular())) {
			datosModificar.setCelular(requestCliente.getCelular());
		}
		if (!requestCliente.getCorreo().equals(datosModificar.getCorreo())) {
			datosModificar.setCorreo(requestCliente.getCorreo());
		}

		return datosModificar;
	}

	public Banco validarBanco(Banco datosModificar, RequestBancoDto requestBanco) {
		if (!(datosModificar.getNombreBanco() == (requestBanco.getNombre_banco()))) {
			datosModificar.setNombreBanco(requestBanco.getNombre_banco());
		}
		return datosModificar;
	}

	public TipoDocumento validarTipoDocumento(TipoDocumento datosModificar,
			RequestTipoDocumentoDto requestTipoDocumento) {
		if (!(requestTipoDocumento.getIdTipoDocumento() == (datosModificar.getIdTipoDocumento()))) {
			datosModificar.setIdTipoDocumento(requestTipoDocumento.getIdTipoDocumento());
		}
		if (!(requestTipoDocumento.getTipoDocumento().equals(datosModificar.getTipoDocumento()))) {
			datosModificar.setTipoDocumento(requestTipoDocumento.getTipoDocumento());
		}
		return datosModificar;
	}

	public TipoMovimiento validarTipoMovimiento(TipoMovimiento datosModificar,
			RequestTipoMovimientosDto requestTipoMovimientos) {
		if (!(requestTipoMovimientos.getTiposMovimientos().equals(datosModificar.getTiposMovimientos()))) {
			datosModificar.setTiposMovimientos(requestTipoMovimientos.getTiposMovimientos());
		}

		return datosModificar;

	}

	public Movimiento validarMovimientos(RequestMovimientosDto requestMovimiento, IMovimientosDao movimientosDao,
			ICuentaDao cuentaDao, ITipoMoviemientosDao tipoMoviemientosDao, IBancoDao bancoDao) {
		Validaciones validaciones = new Validaciones();
		Optional<Movimiento> movimientoData = validaciones.validarExisteMovimiento(requestMovimiento, movimientosDao);

		Movimiento datosModificar = movimientoData.get();
		datosModificar.getIdMovimientos();

		if (!(datosModificar.getCuenta().getIdCuenta() == requestMovimiento.getCuenta().getId())) {
			Optional<Cuenta> cuentaEntity = validaciones.validarExisteCuenta(requestMovimiento, cuentaDao);
			datosModificar.setCuenta(cuentaEntity.get());
		}

		if (!(datosModificar.getBanco().getIdBanco() == requestMovimiento.getBanco().getIdBanco())) {
			Optional<Banco> bancoEntity = validaciones.validarExisteBanco(requestMovimiento, bancoDao);
			datosModificar.setBanco(bancoEntity.get());
		}

		if (!(datosModificar.getCuentaDestinatario().equals(requestMovimiento.getCuentaDestinatario()))) {
			datosModificar.setCuentaDestinatario(requestMovimiento.getCuentaDestinatario());
		}

		if (!(datosModificar.getFecha().equals(requestMovimiento.getFecha()))) {
			datosModificar.setFecha(requestMovimiento.getFecha());
		}

		if (!(datosModificar.getNombreEmpresa().equals(requestMovimiento.getNombreEmpresa()))) {
			datosModificar.setNombreEmpresa(requestMovimiento.getNombreEmpresa());
		}

		if (!(datosModificar.getTipoMovimiento().getIdTipoMovimientos() == requestMovimiento.getTipoMovimiento()
				.getIdTipoMovimientos())) {
			Optional<TipoMovimiento> tipoMovimientoEntity = validaciones.validarExisteTipoMovimiento(requestMovimiento,
					tipoMoviemientosDao);
			datosModificar.setTipoMovimiento(tipoMovimientoEntity.get());
		}

		if (!(datosModificar.getValor() == requestMovimiento.getValor())) {
			datosModificar.setValor(requestMovimiento.getValor());
		}
		return datosModificar;
	}

	public Cuenta validarCuenta(RequestCuentaDto requestCuenta, Cuenta datosModificar, Optional<Cuenta> cuentaData,
			IBancoDao bancoDao, ITiposCuentaDao tiposCuentaDao) {
		java.util.Date fecha = new Date();
		if (!cuentaData.isPresent()) {
			throw new NoSuchElementException(Constantes.MENSAJE_NULO);
		}

		if (!(requestCuenta.getNumeroCuenta() == (datosModificar.getNumeroCuenta()))) {
			datosModificar.setNumeroCuenta(requestCuenta.getNumeroCuenta());
		}
		if (!(requestCuenta.getFechaCreacion().equals(datosModificar.getFechaCreacion()))) {
			datosModificar.setFechaCreacion(fecha);
		}
		if (!(requestCuenta.getTiposCuenta()
				.getIdTiposCuenta() == (datosModificar.getTiposCuenta().getIdTiposCuenta()))) {
			Optional<TiposCuenta> tiposCuentaEntity = tiposCuentaDao
					.findById(datosModificar.getTiposCuenta().getIdTiposCuenta());
			if (!tiposCuentaEntity.isPresent()) {
				throw new DuplicateKeyException(Constantes.IDTIPOCUENTA_INEXISTENTE);
			} else
				datosModificar.setTiposCuenta(tiposCuentaEntity.get());
		}
		if (!requestCuenta.getSaldo().equals(datosModificar.getSaldo())) {
			datosModificar.setSaldo(requestCuenta.getSaldo());
		}
		if (!(requestCuenta.getEstado() == (datosModificar.getEstado()))) {
			datosModificar.setEstado(requestCuenta.getEstado());
		}
		if (!(requestCuenta.getBanco().getIdBanco() == (datosModificar.getBanco().getIdBanco()))) {
			Optional<Banco> bancoEntity = bancoDao.findById(requestCuenta.getBanco().getIdBanco());
			if (!bancoEntity.isPresent()) {
				throw new DuplicateKeyException(Constantes.IDBANCO_INEXISTENTE);
			} else
				datosModificar.setBanco(bancoEntity.get());
		}

		return datosModificar;
	}
}
