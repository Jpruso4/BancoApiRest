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
public class CuentaModel {
	private Integer id;
	private String numeroCuenta;
	private Date fechaCreacion;
	private TiposCuentaModel idTiposCuenta;
	private BigDecimal saldo;
	private Integer estado;
	private BancoModel banco;
}
