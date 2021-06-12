package com.bank.runner.application.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TipoimpuestoModel {
	private int id_impuesto;
	private String nombre_impuesto;
	private double porc_impuesto;
		
}