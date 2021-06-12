package com.bank.runner.application.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipos_cuentas database table.
 * 
 */
@Entity
@Table(name="tipos_cuentas")
@NamedQuery(name="TiposCuenta.findAll", query="SELECT t FROM TiposCuenta t")
public class TiposCuenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipos_cuenta")
	private int idTiposCuenta;

	@Lob
	private String descripcion;

	private int estado;

	@Column(name="tipo_cuenta")
	private String tipoCuenta;

	//bi-directional many-to-one association to Cuenta
	@OneToMany(mappedBy="tiposCuenta")
	private List<Cuenta> cuentas;

	public TiposCuenta() {
	}

	public int getIdTiposCuenta() {
		return this.idTiposCuenta;
	}

	public void setIdTiposCuenta(int idTiposCuenta) {
		this.idTiposCuenta = idTiposCuenta;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getTipoCuenta() {
		return this.tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public List<Cuenta> getCuentas() {
		return this.cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Cuenta addCuenta(Cuenta cuenta) {
		getCuentas().add(cuenta);
		cuenta.setTiposCuenta(this);

		return cuenta;
	}

	public Cuenta removeCuenta(Cuenta cuenta) {
		getCuentas().remove(cuenta);
		cuenta.setTiposCuenta(null);

		return cuenta;
	}

}