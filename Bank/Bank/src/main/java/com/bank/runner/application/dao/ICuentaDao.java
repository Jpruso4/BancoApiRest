package com.bank.runner.application.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.runner.application.entities.Cuenta;

public interface ICuentaDao extends JpaRepository<Cuenta, Integer> {

	@Query(value = "SELECT cuenta FROM Cuenta cuenta WHERE idCuenta = :idCuenta", nativeQuery = false)
	public Cuenta obtenerCuenta(@Param("idCuenta") int idCuenta);

	@Query(value = "SELECT cuenta FROM Cuenta cuenta join fetch cuenta.tiposCuenta", nativeQuery = false)
	public Cuenta obtenerCuentaFetchTiposCuenta(@Param("idCuenta") int idCuenta);

	@Query(value = "SELECT cuenta FROM Cuenta cuenta join fetch cuenta.banco", nativeQuery = false)
	public Cuenta obtenerCuentaFetchBanco(@Param("idCuenta") int idCuenta);

	@Query(value = "SELECT cuenta FROM Cuenta cuenta WHERE numeroCuenta =:numeroCuenta", nativeQuery = false)
	public Cuenta obtenerIdCuenta(@Param("numeroCuenta") String numeroCuenta);
	
	@Query(value = "SELECT cuenta FROM Cuenta cuenta WHERE cuenta.numeroCuenta = :NumeroCuenta", nativeQuery = false)
	public Optional<Cuenta> obtenerCuentaNumeroCuenta(@Param("NumeroCuenta") String NumeroCuenta);

}
