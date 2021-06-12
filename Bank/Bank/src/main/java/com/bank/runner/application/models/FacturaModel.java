package com.bank.runner.application.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaModel {
	private int id_factura;
	private String numeroCuenta;
	private EstadoModel id_estado;
	private Date fecha_emision;
	private Date fecha_vencimiento;
	private double subtotal_factura;
	private TipoimpuestoModel id_impuesto;
	private double total_fact;
}
