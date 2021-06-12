package com.bank.runner.application.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.bank.runner.application.models.BancoModel;
import com.bank.runner.application.models.CuentaModel;
import com.bank.runner.application.models.TipoMovimientosModel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestMovimientosDto {
	@NotBlank(message = "The field can not be null")
    private int idMovimientos;
    private CuentaModel cuenta;
    private BancoModel banco;
    private String cuentaDestinatario;
    private Date fecha;
    private String nombreEmpresa;
    private TipoMovimientosModel tipoMovimiento;
    private BigDecimal valor;
}
