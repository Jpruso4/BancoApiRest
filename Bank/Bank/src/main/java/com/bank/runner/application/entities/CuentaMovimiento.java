package com.bank.runner.application.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cuenta_movimientos database table.
 * 
 */
@Entity
@Table(name="cuenta_movimientos")
@NamedQuery(name="CuentaMovimiento.findAll", query="SELECT c FROM CuentaMovimiento c")
public class CuentaMovimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_cuenta_movimientos")
	private int idCuentaMovimientos;

	//bi-directional many-to-one association to Cuenta
	@ManyToOne
	@JoinColumn(name="id_cuenta")
	private Cuenta cuenta;

	//bi-directional many-to-one association to Movimiento
	@ManyToOne
	@JoinColumn(name="id_movimientos")
	private Movimiento movimiento;

	public CuentaMovimiento() {
	}

	public int getIdCuentaMovimientos() {
		return this.idCuentaMovimientos;
	}

	public void setIdCuentaMovimientos(int idCuentaMovimientos) {
		this.idCuentaMovimientos = idCuentaMovimientos;
	}

	public Cuenta getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Movimiento getMovimiento() {
		return this.movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}

}