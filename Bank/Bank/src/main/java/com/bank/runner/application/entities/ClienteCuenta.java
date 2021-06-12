package com.bank.runner.application.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cliente_cuenta database table.
 * 
 */
@Entity
@Table(name="cliente_cuenta")
@NamedQuery(name="ClienteCuenta.findAll", query="SELECT c FROM ClienteCuenta c")
public class ClienteCuenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_cliente_cuenta")
	private int idClienteCuenta;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Cuenta
	@ManyToOne
	@JoinColumn(name="id_cuenta")
	private Cuenta cuenta;

	public ClienteCuenta() {
	}

	public int getIdClienteCuenta() {
		return this.idClienteCuenta;
	}

	public void setIdClienteCuenta(int idClienteCuenta) {
		this.idClienteCuenta = idClienteCuenta;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cuenta getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

}