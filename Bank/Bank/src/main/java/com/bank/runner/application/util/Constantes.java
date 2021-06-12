package com.bank.runner.application.util;

public class Constantes {
	private Constantes() {
		
	}
	// Controlador
		public static final String CLIENTE_CONTROLLER = "/clientes";
		public static final String TIPO_MOVIMIENTOS_CONTROLLER = "/tipoMovimientos";
		public static final String TIPODOCUMENTO_CONTROLLER = "/tipoDocumentos";
		public static final String BANCO_CONTROLLER = "/bancos";
		public static final String TIPOSCUENTA_CONTROLLER = "/tipocuentas";
		public static final String MOVIMIENTOS_CONTROLLER = "/movimientos";
		public static final String CUENTA_CONTROLLER = "/cuentas";
		public static final String PAGO_CONTROLLER = "/pagos";
	//Mensajes
		 public static final String MENSAJE_REGISTRAR = "Se realizó el registro correctamente";
		 public static final String MENSAJE_DE_ACTUALIZACION_ESTADO_EXITOSA = "El estado ha sido actualizado correctamente";
		 public static final String MENSAJE_DE_ACTUALIZACION_EXITOSA = "Sé actualizó correctamente";
		 public static final String MENSAJE_ELIMINACION_EXITOSA = "Sé eliminó correctamente"; 
	//Codigos mensajes
		 public static final int COD_RESPUESTA_REGISTRO = 001;
		 public static final int COD_RESPUESTA_ACTUALIZAR = 002;
		 public static final int COD_RESPUESTA_ELIMINAR = 003;
		 public static final int COD_RESPUESTA_ELIMINAR_TIPODOCUMENTO = 004;
	//Estados tablas
		 public static final byte ESTADO_ACTIVO = 1;
		 public static final byte ESTADO_INACTIVO = 0;
	// Mensajes Error
	     public static final String MENSAJE_NULO = "Sin resultados en la consulta";
	     public static final String MODIFICACIÓN_DE_DINERO_DE_CUENTA= "No se pudo modificar";
		 public static final String IDClIENTE_DUPLICADO = "Ya existe usuario con ese Id";
		 public static final String IDCUENTA_DUPLICADA = "Ya existe una cuenta con ese Id";
		 public static final String IDBANCO_INEXISTENTE = "El registro de este Banco no existe";
		 public static final String IDTIPOCUENTA_INEXISTENTE = "El registro de este Tipo de cuenta no existe";
		 public static final String NUMERO_DE_CUENTA_INEXISTENTE = "El número de cuenta no existe";
		 public static final String TIPO_DE_MOVIMIENTO_INEXISTENTE = "El tipo de movimiento no existe";
		 public static final String CLIENTE_INEXISTENTE = "El Nùmero de documento ingresado para el Cliente no existe";
		 public static final String TIPO_DOCUMENTO_INEXISTENTE = "El ID para el típo de docuemnto no existe";
		 public static final String PAGO_NO_EFECTUADO = "El pago no pudo ser procesado";
		 
	//Modelos Cvs
		 //Movimientos
		 public static final String[] csvHeaderMovimiento = {"ID Movimientos", "Cuenta", "Numero de Cuenta", "Banco", "Nombre de Banco", "Cuenta Destinatario", "Fecha", "Nombre de la Empresa", "id Tipo de Movimiento", "Nombre tipo Movimiento", "Fecha Movimiento", "Valor"};
		 public static final String[] nameMappingMovimiento = {"idMovimientos", "cuenta", "numeroCuenta","banco", "nombreBanco", "cuentaDestinatario", "fecha", "nombreEmpresa", "tipoMovimiento", "nombreMovimiento", "fecha","valor" };
		 //Cliente
		 public static final String[] csvHeaderCliente = {"ID Cliente", "ID Tipo Documento", "Número de Documento", "Primer Nombre", "Segundo Nombre", "Primer Apellido", "Segundo Apellido", "Dirección", "Telefóno", "Celular", "Correo Electronico", "Estado"};
		 public static final String[] nameMappingCliente = {"idCliente", "idTipoDocumento", "numeroDocumento", "primerNombre", "segundoNombre", "primerApellido", "segundoApellido", "direccion", "telefono", "celular", "correo", "estado"};
}	
