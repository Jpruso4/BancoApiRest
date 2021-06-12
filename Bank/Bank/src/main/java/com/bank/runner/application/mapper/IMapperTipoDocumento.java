package com.bank.runner.application.mapper;

import com.bank.runner.application.dto.ResponseTipoDocumentoDto;
import com.bank.runner.application.entities.TipoDocumento;

public interface IMapperTipoDocumento {
	public ResponseTipoDocumentoDto mostrarTipoDocumento (TipoDocumento atributosDocumento);
}
