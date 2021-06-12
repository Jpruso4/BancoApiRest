package com.bank.runner.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.runner.application.entities.Cliente;
import com.bank.runner.application.entities.ClienteCuenta;

public interface ICliente_cuenta extends JpaRepository<ClienteCuenta, Cliente>{

}
