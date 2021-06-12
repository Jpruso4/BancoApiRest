package com.bank.runner.application.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import com.bank.runner.application.dao.IBancoDao;
import com.bank.runner.application.dao.ICuentaDao;
import com.bank.runner.application.dao.ICuentaMovimientosDao;
import com.bank.runner.application.dao.IMovimientosDao;
import com.bank.runner.application.dao.ITipoMoviemientosDao;
import com.bank.runner.application.dto.RequestMovimientosDto;
import com.bank.runner.application.dto.ResponseMensajeDto;
import com.bank.runner.application.dto.ResponseMovimientosDto;
import com.bank.runner.application.entities.Banco;
import com.bank.runner.application.entities.Cuenta;
import com.bank.runner.application.entities.CuentaMovimiento;
import com.bank.runner.application.entities.Movimiento;
import com.bank.runner.application.entities.TipoMovimiento;
import com.bank.runner.application.mapper.impl.MapperMovimientos;
import com.bank.runner.application.service.IMovimientosService;
import com.bank.runner.application.util.Constantes;
import com.bank.runner.application.util.Validaciones;
import com.bank.runner.application.util.ValidacionesActualizarServicios;
import com.bank.runner.application.util.files.GeneradorDeCsv;
import com.bank.runner.application.util.files.MovimientosCsv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimientosService implements IMovimientosService {
	
	private final IMovimientosDao movimientosDao;
	private final ICuentaDao cuentaDao;
	private final ITipoMoviemientosDao tipoMoviemientosDao;
	private final IBancoDao bancoDao;
	private final ICuentaMovimientosDao cuentaMovimientosDao;
	private final Validaciones validaciones;
	java.util.Date fecha = new Date();
	final MapperMovimientos mapperMovimientos = new MapperMovimientos();
	
	@Autowired
	public MovimientosService(IMovimientosDao movimientosDao, ICuentaDao cuentaDao, ICuentaMovimientosDao cuentaMovimientosDao,ITipoMoviemientosDao tipoMoviemientosDao, IBancoDao bancoDao, Validaciones validaciones) {
		this.movimientosDao = movimientosDao;
		this.cuentaDao = cuentaDao;
		this.tipoMoviemientosDao = tipoMoviemientosDao;
		this.bancoDao = bancoDao;
		this.cuentaMovimientosDao = cuentaMovimientosDao;
		this.validaciones = validaciones;
	}
	
	@Override
	public ResponseMovimientosDto mostrarMovimiento(Integer idMovimiento) {
		Optional<Movimiento> movimientoData = movimientosDao.findById(idMovimiento);
		
		if (!movimientoData.isPresent()) {
			throw new NoSuchElementException(Constantes.MENSAJE_NULO);
		}
		Movimiento movimiento = movimientoData.get();
		return mapperMovimientos.mostrarMovimiento(movimiento);
	}

	@Override
	public List<ResponseMovimientosDto> mostrarListaMovimientos() {
		List<ResponseMovimientosDto> movimientos = new LinkedList<>();
		List<Movimiento> movimientoEntities = movimientosDao.findAll();
		for (Movimiento movimiento : movimientoEntities) {
			movimientos.add(mapperMovimientos.mostrarMovimiento(movimiento));
			
		}
		return movimientos;
	}

	@Override
	public ResponseMensajeDto registrarMovimiento(RequestMovimientosDto datosMovimientoNuevo) {
		Movimiento registroMovimiento = new Movimiento();
		ResponseMensajeDto respuestaMensaje = new ResponseMensajeDto();
		CuentaMovimiento registroCuentaMovimiento = new CuentaMovimiento();
		
		Optional<Banco> bancoEntity = validaciones.validarExisteBanco(datosMovimientoNuevo, bancoDao);
		Optional<Cuenta> cuentaEntity = validaciones.validarExisteCuenta(datosMovimientoNuevo, cuentaDao);
		Optional<TipoMovimiento> tipoMovimientoEntity = validaciones.validarExisteTipoMovimiento(datosMovimientoNuevo ,tipoMoviemientosDao);
		
		registroMovimiento.setCuenta(cuentaEntity.get());
		registroMovimiento.setBanco(bancoEntity.get());
		registroMovimiento.setCuentaDestinatario(datosMovimientoNuevo.getCuentaDestinatario());
		registroMovimiento.setFecha(fecha);
		registroMovimiento.setNombreEmpresa(datosMovimientoNuevo.getNombreEmpresa());
		registroMovimiento.setTipoMovimiento(tipoMovimientoEntity.get());
		registroMovimiento.setValor(datosMovimientoNuevo.getValor());
		movimientosDao.save(registroMovimiento);
		Optional<Movimiento> movimientoEntity = validaciones.validarExisteMovimiento(datosMovimientoNuevo, movimientosDao);
		registroCuentaMovimiento.setCuenta(cuentaEntity.get());
		registroCuentaMovimiento.setMovimiento(movimientoEntity.get()); 
		cuentaMovimientosDao.save(registroCuentaMovimiento);
		respuestaMensaje.setCodigoRespuesta(Constantes.COD_RESPUESTA_REGISTRO);
		respuestaMensaje.setMensajeRespuesta(Constantes.MENSAJE_REGISTRAR);
		return respuestaMensaje;
	}

	@Override
	public ResponseMensajeDto actualizarDatos(RequestMovimientosDto requestMovimiento) {
		ResponseMensajeDto respuestaMensaje = new ResponseMensajeDto();	
		ValidacionesActualizarServicios validacionesActualizarServicios = new ValidacionesActualizarServicios();
		Movimiento datosModificar = validacionesActualizarServicios.validarMovimientos(requestMovimiento, movimientosDao,cuentaDao, tipoMoviemientosDao, bancoDao);
		movimientosDao.save(datosModificar);
		respuestaMensaje.setCodigoRespuesta(Constantes.COD_RESPUESTA_ACTUALIZAR);
		respuestaMensaje.setMensajeRespuesta(Constantes.MENSAJE_DE_ACTUALIZACION_EXITOSA);
		return respuestaMensaje;
	}
	
	public ResponseMensajeDto eliminarMovimiento(int idMovimiento) {
		ResponseMensajeDto respuestaMensaje =  new ResponseMensajeDto();
		Optional<Movimiento> movimientoData = movimientosDao.findById(idMovimiento);
		if(!movimientoData.isPresent()) {
			throw new NoSuchElementException(Constantes.MENSAJE_NULO);
		}else {
			movimientosDao.deleteById(idMovimiento);
			respuestaMensaje.setCodigoRespuesta(Constantes.COD_RESPUESTA_ELIMINAR);
			respuestaMensaje.setMensajeRespuesta(Constantes.MENSAJE_ELIMINACION_EXITOSA);
			return respuestaMensaje;
		}
	}
	
	public String mostrarCsvDeMovimientos(HttpServletResponse response)throws IOException {
		List<ResponseMovimientosDto> listMovimientos = mostrarListaMovimientos();
		
		GeneradorDeCsv generadorDeCsv = new GeneradorDeCsv();
		
		List<MovimientosCsv> recods = new LinkedList<>();
		listMovimientos.stream().forEach(movimiento -> {
			MovimientosCsv movimientoCsv = new MovimientosCsv(
					String.valueOf(movimiento.getIdMovimientos()), 
					String.valueOf(movimiento.getCuenta().getId()), 
					movimiento.getCuenta().getNumeroCuenta(),
					String.valueOf(movimiento.getBanco().getIdBanco()),
					movimiento.getBanco().getNombreBanco(),
					String.valueOf(movimiento.getTipoMovimiento().getIdTipoMovimientos()),
					movimiento.getTipoMovimiento().getTipoMovimientos(),
					movimiento.getCuentaDestinatario(),
					movimiento.getNombreEmpresa(),
					String.valueOf(movimiento.getFecha()),
					String.valueOf(movimiento.getValor())
					);
		
			recods.add(movimientoCsv);
		});
		
		return generadorDeCsv.CrearArchivo(recods , response);
	}	
}
