package com.bank.runner.application.mapper;

import com.bank.runner.application.dto.ResponseBancoDto;
import com.bank.runner.application.entities.Banco;

public interface IMapperBanco {
	public ResponseBancoDto listarBancos(Banco listaBancos);

}
