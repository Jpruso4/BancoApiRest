package com.bank.runner.application.util.files;

public class MovimientosCsv {
	private String idMovimientos;
	private String cuenta;
	private String numeroCuenta;
	private String banco;
	private String nombreBanco;
	private String tipoMovimiento;
	private String nombreMovimiento;
	private String cuentaDestinatario;
	private String nombreEmpresa;
	private String fecha;
	private String valor;

	public MovimientosCsv(String idMovimientos, String cuenta, String numeroCuenta, String banco, String nombreBanco,
			String tipoMovimiento, String nombreMovimiento, String cuentaDestinatario, String nombreEmpresa,
			String fecha, String valor) {
		super();
		this.idMovimientos = idMovimientos;
		this.cuenta = cuenta;
		this.numeroCuenta = numeroCuenta;
		this.banco = banco;
		this.nombreBanco = nombreBanco;
		this.tipoMovimiento = tipoMovimiento;
		this.nombreMovimiento = nombreMovimiento;
		this.cuentaDestinatario = cuentaDestinatario;
		this.nombreEmpresa = nombreEmpresa;
		this.fecha = fecha;
		this.valor = valor;
	}
	
	public String getNombreMovimiento() {
		return nombreMovimiento;
	}

	public void setNombreMovimiento(String nombreMovimiento) {
		this.nombreMovimiento = nombreMovimiento;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public String getCuentaDestinatario() {
		return cuentaDestinatario;
	}

	public void setCuentaDestinatario(String cuentaDestinatario) {
		this.cuentaDestinatario = cuentaDestinatario;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getIdMovimientos() {
		return idMovimientos;
	}

	public void setIdMovimientos(String idMovimientos) {
		this.idMovimientos = idMovimientos;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

}
