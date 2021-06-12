package com.bank.runner.application.mapper.impl;

import org.springframework.stereotype.Service;

import com.bank.runner.application.models.BancoModel;
import com.bank.runner.application.models.CuentaModel;
import com.bank.runner.application.models.TipoMovimientosModel;
import com.bank.runner.application.dto.ResponseMovimientosDto;
import com.bank.runner.application.entities.Banco;
import com.bank.runner.application.entities.Cuenta;
import com.bank.runner.application.entities.Movimiento;
import com.bank.runner.application.entities.TipoMovimiento;
import com.bank.runner.application.mapper.IMapperMovimientos;


@Service
public class MapperMovimientos implements IMapperMovimientos {

	@Override
	public ResponseMovimientosDto mostrarMovimiento(Movimiento movimientoEntity) {
		ResponseMovimientosDto mostrarLista = new ResponseMovimientosDto();
		mostrarLista.setIdMovimientos(movimientoEntity.getIdMovimientos());
		
		Cuenta cuenta = movimientoEntity.getCuenta();
		CuentaModel cuentaModel = new CuentaModel();
		
		Banco banco = movimientoEntity.getBanco();
		BancoModel bancoModel =  new BancoModel();
		
		TipoMovimiento tipoMovimientos = movimientoEntity.getTipoMovimiento();
		TipoMovimientosModel tipoMovimientosModel = new TipoMovimientosModel();
		
		cuentaModel.setId(cuenta.getIdCuenta());
		cuentaModel.setNumeroCuenta(cuenta.getNumeroCuenta());
		bancoModel.setIdBanco(banco.getIdBanco());
		bancoModel.setNombreBanco(banco.getNombreBanco());
		tipoMovimientosModel.setIdTipoMovimientos(tipoMovimientos.getIdTipoMovimientos());
		tipoMovimientosModel.setTipoMovimientos(tipoMovimientos.getTiposMovimientos());
		
		mostrarLista.setCuenta(cuentaModel);
		mostrarLista.setBanco(bancoModel);
		mostrarLista.setTipoMovimiento(tipoMovimientosModel);
		mostrarLista.setCuentaDestinatario(movimientoEntity.getCuentaDestinatario());
		mostrarLista.setFecha(movimientoEntity.getFecha());
		mostrarLista.setNombreEmpresa(movimientoEntity.getNombreEmpresa());
		mostrarLista.setValor(movimientoEntity.getValor());
		return mostrarLista;
	}
}
