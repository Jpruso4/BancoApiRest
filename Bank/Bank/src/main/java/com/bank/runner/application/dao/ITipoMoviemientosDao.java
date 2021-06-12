package com.bank.runner.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.runner.application.entities.TipoMovimiento;
import com.bank.runner.application.entities.TiposCuenta;

public interface ITipoMoviemientosDao extends JpaRepository<TipoMovimiento, Integer> {
	@Query(value ="SELECT tipoMovimientos FROM TipoMovimiento tipoMovimientos where tipoMovimientos.idTipoMovimientos = :identificacionTipoMovimientos", nativeQuery=false)
	public TiposCuenta obtenerTipoMovimientos(@Param ("identificacionTipoMovimientos")int TipoMovimiento);
}
