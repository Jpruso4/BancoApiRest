package com.bank.runner.application.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.bank.runner.application.models.BancoModel;
import com.bank.runner.application.models.TiposCuentaModel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseCuentaDto {
	private int idCuenta;
	private String numeroCuenta;
	private Date fechaCreacion;
	private TiposCuentaModel TiposCuenta;
	private BigDecimal saldo;
	private int estado;
	private BancoModel banco;
	
	//private Estado estado;
	//private CuentaModel cuenta;
}
