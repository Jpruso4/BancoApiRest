package com.bank.runner.application.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bank.runner.application.dao.IParametriaDao;
import com.bank.runner.application.entities.Parametria;
import com.bank.runner.application.mapper.impl.MapperParametria;
import com.bank.runner.application.models.ParametriaModel;
import com.bank.runner.application.service.IParametriaService;

@Service
public class ParametriaService implements IParametriaService{
	
	private final IParametriaDao parametriaDao;
	final MapperParametria mapperParametria = new MapperParametria();
	
	 @Autowired
	 public ParametriaService(IParametriaDao parametriaDao) {
			this.parametriaDao = parametriaDao;
	}

	@Override
	@Cacheable("urls")
	public List<ParametriaModel> consultarParametria() {
		List<ParametriaModel> parametrias = new LinkedList<>();
		Iterable<Parametria> parametriaEntities = parametriaDao.findAll();
		parametrias = StreamSupport.stream(parametriaEntities.spliterator(), false).map((parametria) -> {
			return mapperParametria.mostrarParametria(parametria);
		}).collect(Collectors.toList());
		return parametrias;
	}
}
