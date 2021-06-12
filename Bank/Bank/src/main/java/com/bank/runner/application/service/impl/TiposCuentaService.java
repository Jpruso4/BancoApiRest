package com.bank.runner.application.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.bank.runner.application.dao.ITiposCuentaDao;
import com.bank.runner.application.entities.TiposCuenta;
import com.bank.runner.application.service.ITiposCuentaService;
import com.bank.runner.application.util.Constantes;

@Service
public class TiposCuentaService implements ITiposCuentaService {
	private ITiposCuentaDao tiposCuentaDao;

	public TiposCuentaService(ITiposCuentaDao tiposCuentaDao) {
		this.tiposCuentaDao = tiposCuentaDao;

	}

	@Override
	public TiposCuenta mostrarTipoCuenta(Integer idTipoCuenta) {
		Optional<TiposCuenta> tipoCuenta = tiposCuentaDao.findById(idTipoCuenta);

		if (!tipoCuenta.isPresent()) {
			throw new NoSuchElementException(Constantes.MENSAJE_NULO);
		}
		return tipoCuenta.get();
	}

	@Override
	public List<TiposCuenta> mostrarTiposaCuenta() {
		return tiposCuentaDao.findAll();
	}
}