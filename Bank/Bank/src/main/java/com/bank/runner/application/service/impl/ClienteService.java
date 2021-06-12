package com.bank.runner.application.service.impl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.runner.application.dao.IClienteDao;
import com.bank.runner.application.dao.ITipoDocumentoDao;
import com.bank.runner.application.dto.RequestClienteDto;
import com.bank.runner.application.dto.ResponseClienteDto;
import com.bank.runner.application.dto.ResponseMensajeDto;
import com.bank.runner.application.entities.Cliente;
import com.bank.runner.application.entities.TipoDocumento;
import com.bank.runner.application.mapper.impl.MapperCliente;
import com.bank.runner.application.service.IClienteService;
import com.bank.runner.application.util.Constantes;
import com.bank.runner.application.util.Validaciones;
import com.bank.runner.application.util.ValidacionesActualizarServicios;
import com.bank.runner.application.util.files.ClienteCsv;
import com.bank.runner.application.util.files.GeneradorDeCsv;

@Service
public class ClienteService implements IClienteService {

	private final IClienteDao clienteDao;
	private final ITipoDocumentoDao tipodocumentoDao;
	private final Validaciones validaCliente;

	@Autowired
	public ClienteService(IClienteDao clienteDao, ITipoDocumentoDao tipodocumentoDao, Validaciones validaCliente) {
		this.clienteDao = clienteDao;
		this.tipodocumentoDao = tipodocumentoDao;
		this.validaCliente = validaCliente;
	}

	final MapperCliente mapperCliente = new MapperCliente();

	@Override
	public ResponseClienteDto mostrarCliente(Integer idCliente) {
		ResponseClienteDto responseCliente = new ResponseClienteDto();
		//Validaciones validaCliente = new Validaciones();
		Optional<Cliente> cliente = validaCliente.validarCliente(idCliente, clienteDao);
		responseCliente = mapperCliente.mostrarCliente(cliente.get());
		return responseCliente;
	}

	@Override
	public List<ResponseClienteDto> mostrarListaClientes() {
		List<ResponseClienteDto> clientes = new LinkedList<>();
		List<Cliente> clienteEntities = clienteDao.obtenerClientes();
		for (Cliente cliente : clienteEntities)
			clientes.add(mapperCliente.mostrarCliente(cliente));
		return clientes;
	}

	@Override
	public ResponseMensajeDto registrarCliente(RequestClienteDto datosClienteNuevo) {
		ResponseMensajeDto respuestaPeticion = new ResponseMensajeDto();
		Cliente registroCliente = new Cliente();
		TipoDocumento tipoDocumentoCliente = new TipoDocumento();
		//Validaciones validacionesCliente = new Validaciones();
		validaCliente.validarDuplicidadCliente(datosClienteNuevo, clienteDao);
		Optional<TipoDocumento> tipoDocumento = validaCliente.validarTipoDocumento(datosClienteNuevo,
				tipodocumentoDao);
		tipoDocumentoCliente = tipoDocumento.get();
		registroCliente.setTipoDocumento(tipoDocumentoCliente);
		registroCliente.setNumeroDocumento(datosClienteNuevo.getNumeroDocumento());
		registroCliente.setPrimerNombre(datosClienteNuevo.getPrimerNombre());
		registroCliente.setSegundoNombre(datosClienteNuevo.getSegundoNombre());
		registroCliente.setPrimerApellido(datosClienteNuevo.getPrimerApellido());
		registroCliente.setSegundoApellido(datosClienteNuevo.getSegundoApellido());
		registroCliente.setDireccion(datosClienteNuevo.getDireccion());
		registroCliente.setTelefono(datosClienteNuevo.getTelefono());
		registroCliente.setCelular(datosClienteNuevo.getCelular());
		registroCliente.setCorreo(datosClienteNuevo.getCorreo());
		registroCliente.setEstado(1);
		clienteDao.save(registroCliente);
		respuestaPeticion.setCodigoRespuesta(Constantes.COD_RESPUESTA_REGISTRO);
		respuestaPeticion.setMensajeRespuesta(Constantes.MENSAJE_REGISTRAR);
		return respuestaPeticion;
	}

	@Override
	public ResponseMensajeDto actualizarEstadoCliente(Integer idCliente) {
		ResponseMensajeDto respuestaPeticion = new ResponseMensajeDto();
		Cliente cliente = new Cliente();
		Validaciones validaCliente = new Validaciones();
		Optional<Cliente> clienteValida = validaCliente.validarCliente(idCliente, clienteDao);
		cliente = clienteValida.get();
		CambiarEstadoCliente(cliente);
		clienteDao.save(cliente);
		respuestaPeticion.setCodigoRespuesta(Constantes.COD_RESPUESTA_ACTUALIZAR);
		respuestaPeticion.setMensajeRespuesta(Constantes.MENSAJE_DE_ACTUALIZACION_ESTADO_EXITOSA);
		return respuestaPeticion;
	}

	@Override
	public ResponseMensajeDto actualizarDatos(RequestClienteDto requestCliente) {
		ValidacionesActualizarServicios validaActualizarCliente = new ValidacionesActualizarServicios();
		ResponseMensajeDto respuestaMensaje = new ResponseMensajeDto();
		Validaciones validaCliente = new Validaciones();
		Optional<Cliente> clienteValida = validaCliente.validarCliente(requestCliente.getIdCliente(), clienteDao);
		Cliente datosModificar = clienteValida.get();
		datosModificar.getIdCliente();
		validaActualizarCliente.validarCliente(datosModificar, requestCliente, tipodocumentoDao);
		clienteDao.save(datosModificar);
		respuestaMensaje.setCodigoRespuesta(Constantes.COD_RESPUESTA_ACTUALIZAR);
		respuestaMensaje.setMensajeRespuesta(Constantes.MENSAJE_DE_ACTUALIZACION_EXITOSA);
		return respuestaMensaje;

	}

	private static Cliente CambiarEstadoCliente(Cliente cliente) {
		if (cliente.getEstado() == 1) {
			cliente.setEstado(Constantes.ESTADO_INACTIVO);
		} else {
			cliente.setEstado(Constantes.ESTADO_ACTIVO);
		}
		return cliente;
	}
	
	public String MostrarCsvDeClientes(HttpServletResponse response)throws IOException {
		List<ResponseClienteDto> listClientes = mostrarListaClientes();
		
		GeneradorDeCsv generadorDeCsv = new GeneradorDeCsv();
		
		List<ClienteCsv> recods = new LinkedList<>();
		listClientes.stream().forEach(cliente -> {
				ClienteCsv clienteCsv = new ClienteCsv(
					String.valueOf(cliente.getIdCliente()), 
					String.valueOf(cliente.getTipoDocumento().getIdTipoDocumento()),
					cliente.getNumeroDocumento(),
					cliente.getPrimerNombre(),
					cliente.getSegundoNombre(),
					cliente.getPrimerApellido(),
					cliente.getSegundoApellido(),
					cliente.getDireccion(),
					cliente.getTelefono(),
					cliente.getCelular(),
					cliente.getCorreo(),
					String.valueOf(cliente.getEstado())
					);
		
			recods.add(clienteCsv);
		});
		
		return generadorDeCsv.CrearArchivo(recods , response);
	}	
}
