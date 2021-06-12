package com.bank.runner.application.models;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovimientoModel {
	
	private Integer idMovimientos;
	private CuentaModel cuenta;
	private BancoModel banco;
	private String cuentaDestinatario;
	private Date fecha;
	private String nombreEmpresa;
	private TipoMovimientosModel tipoMovimiento;
	private BigDecimal valor;
}
