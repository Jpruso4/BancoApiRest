package com.bank.runner.application.service.impl;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.bank.runner.application.dao.IClienteDao;
import com.bank.runner.application.dao.ITipoDocumentoDao;
import com.bank.runner.application.dto.ResponseClienteDto;
import com.bank.runner.application.entities.Cliente;
import com.bank.runner.application.entities.TipoDocumento;
import com.bank.runner.application.service.IClienteService;
import com.bank.runner.application.util.Validaciones;

public class ClienteServiceTest {

	private IClienteDao iclienteDao;
	private ITipoDocumentoDao tipodocumentoDao;
	private Validaciones validaciones;

	@Before
	public void setUp() {
		iclienteDao = Mockito.mock(IClienteDao.class);
		tipodocumentoDao = Mockito.mock(ITipoDocumentoDao.class);
		validaciones = new Validaciones();
	}

	@Test(expected = RuntimeException.class)
	public void servicioClienteRecibevaloresNulos() {
		IClienteService clientService = new ClienteService(iclienteDao, tipodocumentoDao, validaciones);
		clientService.mostrarCliente(null);
	}
	@Test

	//cuandoSeLLamaMostracClienteYElNumeroClienteEsValidoDebeRetornarCliente
	public void cuandoSeLLamaMostracClienteYElNumeroClienteEsValidoDebeRetornarCliente() {
		IClienteService clienteService = new ClienteService(iclienteDao, tipodocumentoDao, validaciones);
		ResponseClienteDto responseCliente = new ResponseClienteDto();
		Cliente clientePrueba = new Cliente();
		clientePrueba.setIdCliente(1);
		clientePrueba.setPrimerNombre("UserPrueba");
		clientePrueba.setTipoDocumento(new TipoDocumento());

		Mockito.when(iclienteDao.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(clientePrueba));
		
		ResponseClienteDto responseClienteDto = clienteService.mostrarCliente(1);
		
		Assert.assertNotNull(responseClienteDto);
		Assert.assertEquals(responseClienteDto.getIdCliente(), clientePrueba.getIdCliente());

	}
}