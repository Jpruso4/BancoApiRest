package com.bank.runner.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.runner.application.entities.TipoDocumento;

public interface ITipoDocumentoDao extends JpaRepository <TipoDocumento, Integer>{

}
