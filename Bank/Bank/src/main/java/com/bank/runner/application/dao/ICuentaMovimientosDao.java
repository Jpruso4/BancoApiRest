package com.bank.runner.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.runner.application.entities.Cuenta;
import com.bank.runner.application.entities.CuentaMovimiento;

public interface ICuentaMovimientosDao extends JpaRepository<CuentaMovimiento, Cuenta> {

}
