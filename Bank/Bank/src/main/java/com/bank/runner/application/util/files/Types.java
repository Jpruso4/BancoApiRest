package com.bank.runner.application.util.files;

public class Types {
	public enum ListTypes {
		MOVIMIENTO, 
		BANCO, 
		CLIENTE, 
		CUENTA, 
		TIPODOCUMENTO, 
		TIPOSCUENTA;
	}

	public static <T> ListTypes getType(T type) {
		if (type instanceof MovimientosCsv) {
			return ListTypes.MOVIMIENTO;
		}
		
		if(type instanceof ClienteCsv) {
			return ListTypes.CLIENTE;
		}
		return null;
	}
}
