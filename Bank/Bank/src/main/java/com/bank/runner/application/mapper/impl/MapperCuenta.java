package com.bank.runner.application.mapper.impl;

import org.springframework.stereotype.Service;

import com.bank.runner.application.dto.ResponseCuentaDto;
import com.bank.runner.application.entities.Banco;
import com.bank.runner.application.entities.Cuenta;
import com.bank.runner.application.entities.TiposCuenta;
import com.bank.runner.application.mapper.IMapperCuenta;
import com.bank.runner.application.models.BancoModel;
import com.bank.runner.application.models.TiposCuentaModel;

@Service
public class MapperCuenta implements IMapperCuenta{
	@Override
	public ResponseCuentaDto mostrarCuenta (Cuenta cuentaEntity) {
		ResponseCuentaDto mostrarLista = new ResponseCuentaDto();
		mostrarLista.setIdCuenta(cuentaEntity.getIdCuenta());
		mostrarLista.setNumeroCuenta(cuentaEntity.getNumeroCuenta());
		mostrarLista.setFechaCreacion(cuentaEntity.getFechaCreacion());
		
		TiposCuenta tipos = cuentaEntity.getTiposCuenta();
		TiposCuentaModel tipoCuentaModel = new TiposCuentaModel();
		
		Banco banco = cuentaEntity.getBanco();
		BancoModel bancoModel = new BancoModel();
		
		bancoModel.setIdBanco(banco.getIdBanco());
		tipoCuentaModel.setIdTiposCuenta(tipos.getIdTiposCuenta());
		
		mostrarLista.setTiposCuenta(tipoCuentaModel);
		mostrarLista.setSaldo(cuentaEntity.getSaldo());
		mostrarLista.setEstado(cuentaEntity.getEstado());
		mostrarLista.setBanco(bancoModel);
		return mostrarLista;
	}
}
