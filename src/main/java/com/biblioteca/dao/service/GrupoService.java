package com.biblioteca.dao.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.biblioteca.dao.GrupoDAO;
import com.biblioteca.entitys.Grupo;
import com.biblioteca.util.jpa.Transactional;

public class GrupoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private GrupoDAO grupoDAO;
	
	@Transactional
	public void salvar(Grupo grupo) throws MensagemErroException {
		
		if (grupo.getDescricao() == null || grupo.getDescricao().trim().equals("")) {
			throw new MensagemErroException("A descrição do grupo é obrigatório");
		}
		
		this.grupoDAO.salvar(grupo);
	}

}
