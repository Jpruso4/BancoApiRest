package com.bank.runner.application.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the movimientos database table.
 * 
 */
@Entity
@Table(name="movimientos")
@NamedQuery(name="Movimiento.findAll", query="SELECT m FROM Movimiento m")
public class Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_movimientos")
	private int idMovimientos;

	@Column(name="cuenta_destinatario")
	private String cuentaDestinatario;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="nombre_empresa")
	private String nombreEmpresa;

	private BigDecimal valor;

	//bi-directional many-to-one association to CuentaMovimiento
	@OneToMany(mappedBy="movimiento")
	private List<CuentaMovimiento> cuentaMovimientos;

	//bi-directional many-to-one association to Banco
	@ManyToOne
	@JoinColumn(name="id_banco")
	private Banco banco;

	//bi-directional many-to-one association to Cuenta
	@ManyToOne
	@JoinColumn(name="id_cuenta")
	private Cuenta cuenta;

	//bi-directional many-to-one association to TipoMovimiento
	@ManyToOne
	@JoinColumn(name="id_tipo_movimientos")
	private TipoMovimiento tipoMovimiento;

	public Movimiento() {
	}

	public int getIdMovimientos() {
		return this.idMovimientos;
	}

	public void setIdMovimientos(int idMovimientos) {
		this.idMovimientos = idMovimientos;
	}

	public String getCuentaDestinatario() {
		return this.cuentaDestinatario;
	}

	public void setCuentaDestinatario(String cuentaDestinatario) {
		this.cuentaDestinatario = cuentaDestinatario;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombreEmpresa() {
		return this.nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public List<CuentaMovimiento> getCuentaMovimientos() {
		return this.cuentaMovimientos;
	}

	public void setCuentaMovimientos(List<CuentaMovimiento> cuentaMovimientos) {
		this.cuentaMovimientos = cuentaMovimientos;
	}

	public CuentaMovimiento addCuentaMovimiento(CuentaMovimiento cuentaMovimiento) {
		getCuentaMovimientos().add(cuentaMovimiento);
		cuentaMovimiento.setMovimiento(this);

		return cuentaMovimiento;
	}

	public CuentaMovimiento removeCuentaMovimiento(CuentaMovimiento cuentaMovimiento) {
		getCuentaMovimientos().remove(cuentaMovimiento);
		cuentaMovimiento.setMovimiento(null);

		return cuentaMovimiento;
	}

	public Banco getBanco() {
		return this.banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Cuenta getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public TipoMovimiento getTipoMovimiento() {
		return this.tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

}