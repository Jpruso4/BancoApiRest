package com.bank.runner.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseTiposCuentaDto {
	private  int id_tipos_cuenta;
	private String tipo_cuenta;
	private String descripcion;
	private int estado;
}
