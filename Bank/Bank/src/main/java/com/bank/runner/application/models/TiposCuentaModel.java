package com.bank.runner.application.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TiposCuentaModel {
	
	private Integer idTiposCuenta;

	private String descripcion;

	private Integer estado;

	private String tipoCuenta;
}