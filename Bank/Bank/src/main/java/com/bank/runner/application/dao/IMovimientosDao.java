package com.bank.runner.application.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.runner.application.entities.Movimiento;

public interface IMovimientosDao extends JpaRepository <Movimiento, Integer> {

	@Query(value = "SELECT movimientos FROM Movimiento movimientos where idMovimientos = :idMovimientos", nativeQuery=false)
	public Movimiento obtenerMovimientos(@Param("idMovimientos")int idMovimientos);
	
	@Query(value = "SELECT movimientos FROM Movimiento movimientos join fetch movimientos.tipoMovimiento", nativeQuery=false)
	public Movimiento obtenerMovimientoFetchTipoMovimiento(@Param ("idMovimientos")int idMovimientos);

	@Query(value = "SELECT movimientos FROM Movimiento movimientos join fetch movimientos.banco", nativeQuery=false)
	public Movimiento obtenerMovimientoFetchBanco(@Param ("idMovimientos")int idMovimientos);
	
	@Query(value = "SELECT movimientos FROM Movimiento movimientos WHERE fecha =:fecha", nativeQuery = false)
	public Movimiento obtenerIdMovimiento(@Param("fecha") Date fecha);
	
	@Query(value = "SELECT movimientos FROM Movimiento movimientos join fetch movimientos.cuenta", nativeQuery=false)
	public Movimiento obtenerMovimientoFetchCuenta(@Param ("idMovimientos")int idMovimientos);
	
}
