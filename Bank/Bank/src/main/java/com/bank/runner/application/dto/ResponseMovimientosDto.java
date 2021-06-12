package com.bank.runner.application.dto;

import java.math.BigDecimal;
import java.util.Date;
import com.bank.runner.application.models.BancoModel;
import com.bank.runner.application.models.CuentaModel;
import com.bank.runner.application.models.TipoMovimientosModel;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMovimientosDto {
	private int idMovimientos;
	private CuentaModel cuenta;
	private BancoModel banco;
	private String cuentaDestinatario;
	private Date fecha;
	private String nombreEmpresa;
	private TipoMovimientosModel tipoMovimiento;
	private BigDecimal valor;
}
