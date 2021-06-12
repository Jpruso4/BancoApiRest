package com.bank.runner.application.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_movimientos database table.
 * 
 */
@Entity
@Table(name="tipo_movimientos")
@NamedQuery(name="TipoMovimiento.findAll", query="SELECT t FROM TipoMovimiento t")
public class TipoMovimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo_movimientos")
	private int idTipoMovimientos;

	@Column(name="tipos_movimientos")
	private String tiposMovimientos;

	//bi-directional many-to-one association to Movimiento
	@OneToMany(mappedBy="tipoMovimiento")
	private List<Movimiento> movimientos;

	public TipoMovimiento() {
	}

	public int getIdTipoMovimientos() {
		return this.idTipoMovimientos;
	}

	public void setIdTipoMovimientos(int idTipoMovimientos) {
		this.idTipoMovimientos = idTipoMovimientos;
	}

	public String getTiposMovimientos() {
		return this.tiposMovimientos;
	}

	public void setTiposMovimientos(String tiposMovimientos) {
		this.tiposMovimientos = tiposMovimientos;
	}

	public List<Movimiento> getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Movimiento addMovimiento(Movimiento movimiento) {
		getMovimientos().add(movimiento);
		movimiento.setTipoMovimiento(this);

		return movimiento;
	}

	public Movimiento removeMovimiento(Movimiento movimiento) {
		getMovimientos().remove(movimiento);
		movimiento.setTipoMovimiento(null);

		return movimiento;
	}

}