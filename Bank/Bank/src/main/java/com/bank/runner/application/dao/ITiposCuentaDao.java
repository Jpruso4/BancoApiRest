package com.bank.runner.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.runner.application.entities.TiposCuenta;

public interface ITiposCuentaDao extends JpaRepository<TiposCuenta, Integer>{
	@Query(value ="SELECT tiposCuenta FROM TiposCuenta tiposCuenta where tiposCuenta.idTiposCuenta = :identificacionTiposCuenta", nativeQuery=false)
	public TiposCuenta obtenerTipoCuenta(@Param ("identificacionTiposCuenta")int idTiposCuenta);
}
