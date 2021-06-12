package com.bank.runner.application.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cuenta database table.
 * 
 */
@Entity
@NamedQuery(name="Cuenta.findAll", query="SELECT c FROM Cuenta c")
public class Cuenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_cuenta")
	private int idCuenta;

	private int estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="numero_cuenta")
	private String numeroCuenta;

	private BigDecimal saldo;

	//bi-directional many-to-one association to ClienteCuenta
	@OneToMany(mappedBy="cuenta")
	private List<ClienteCuenta> clienteCuentas;

	//bi-directional many-to-one association to Banco
	@ManyToOne
	@JoinColumn(name="id_banco")
	private Banco banco;

	//bi-directional many-to-one association to TiposCuenta
	@ManyToOne
	@JoinColumn(name="id_tipos_cuenta")
	private TiposCuenta tiposCuenta;

	//bi-directional many-to-one association to CuentaMovimiento
	@OneToMany(mappedBy="cuenta")
	private List<CuentaMovimiento> cuentaMovimientos;

	//bi-directional many-to-many association to Producto
	@ManyToMany
	@JoinTable(
		name="cuenta_productos"
		, joinColumns={
			@JoinColumn(name="id_cuenta")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_producto")
			}
		)
	private List<Producto> productos;

	//bi-directional many-to-one association to Movimiento
	@OneToMany(mappedBy="cuenta")
	private List<Movimiento> movimientos;

	public Cuenta() {
	}

	public int getIdCuenta() {
		return this.idCuenta;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNumeroCuenta() {
		return this.numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public BigDecimal getSaldo() {
		return this.saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public List<ClienteCuenta> getClienteCuentas() {
		return this.clienteCuentas;
	}

	public void setClienteCuentas(List<ClienteCuenta> clienteCuentas) {
		this.clienteCuentas = clienteCuentas;
	}

	public ClienteCuenta addClienteCuenta(ClienteCuenta clienteCuenta) {
		getClienteCuentas().add(clienteCuenta);
		clienteCuenta.setCuenta(this);

		return clienteCuenta;
	}

	public ClienteCuenta removeClienteCuenta(ClienteCuenta clienteCuenta) {
		getClienteCuentas().remove(clienteCuenta);
		clienteCuenta.setCuenta(null);

		return clienteCuenta;
	}

	public Banco getBanco() {
		return this.banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public TiposCuenta getTiposCuenta() {
		return this.tiposCuenta;
	}

	public void setTiposCuenta(TiposCuenta tiposCuenta) {
		this.tiposCuenta = tiposCuenta;
	}

	public List<CuentaMovimiento> getCuentaMovimientos() {
		return this.cuentaMovimientos;
	}

	public void setCuentaMovimientos(List<CuentaMovimiento> cuentaMovimientos) {
		this.cuentaMovimientos = cuentaMovimientos;
	}

	public CuentaMovimiento addCuentaMovimiento(CuentaMovimiento cuentaMovimiento) {
		getCuentaMovimientos().add(cuentaMovimiento);
		cuentaMovimiento.setCuenta(this);

		return cuentaMovimiento;
	}

	public CuentaMovimiento removeCuentaMovimiento(CuentaMovimiento cuentaMovimiento) {
		getCuentaMovimientos().remove(cuentaMovimiento);
		cuentaMovimiento.setCuenta(null);

		return cuentaMovimiento;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<Movimiento> getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Movimiento addMovimiento(Movimiento movimiento) {
		getMovimientos().add(movimiento);
		movimiento.setCuenta(this);

		return movimiento;
	}

	public Movimiento removeMovimiento(Movimiento movimiento) {
		getMovimientos().remove(movimiento);
		movimiento.setCuenta(null);

		return movimiento;
	}

}