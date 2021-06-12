package com.bank.runner.application.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParametriaModel {
	private int idParametria;
	private String seccion;
	private String subseccion;
	private String url;
	private String valor;
}	

