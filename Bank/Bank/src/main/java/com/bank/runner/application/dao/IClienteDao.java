 package com.bank.runner.application.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.runner.application.entities.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Integer> {
	@Query(value = "SELECT cliente FROM Cliente cliente WHERE cliente.numeroDocumento = :numerodocumento", nativeQuery = false)
	public Optional<Cliente> obtenerCliente(@Param("numerodocumento") String numerodocumento);

	@Query(value = "SELECT cliente FROM Cliente cliente WHERE cliente.numeroDocumento = :numerodocumento", nativeQuery = false)
	public Cliente obtenerIdCliente(@Param("numerodocumento") int numerodocumento);
	
	@Query(value = "SELECT cliente FROM Cliente cliente WHERE cliente.estado = 1", nativeQuery = false)
	public List<Cliente> obtenerClientes();
	
	@Query(value = "SELECT cliente FROM Cliente cliente WHERE cliente.idCliente = :idCliente", nativeQuery = false)
	public Cliente obtenerClienteId(@Param("idCliente") int idCliente);
}
