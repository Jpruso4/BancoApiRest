package com.bank.runner.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.runner.application.entities.Banco;

public interface IBancoDao extends JpaRepository<Banco, Integer> {
	@Query(value ="SELECT banco FROM Banco banco where banco.idBanco = :identificacionBanco", nativeQuery=false)
	public Banco obtenerBanco(@Param ("identificacionBanco")int idBanco);
}