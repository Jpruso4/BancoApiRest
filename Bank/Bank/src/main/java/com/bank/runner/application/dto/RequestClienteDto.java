package com.bank.runner.application.dto;

import com.bank.runner.application.models.TipoDocumentoModel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestClienteDto {
	    private int idCliente;
	    private TipoDocumentoModel tipoDocumento;
		private String numeroDocumento;
		private String primerNombre;
		private String segundoNombre;
		private String primerApellido;
		private String segundoApellido;
		private String direccion;
		private String telefono;
		private String celular;
		private String correo;
		private int estado;
}
