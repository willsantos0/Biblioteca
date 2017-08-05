package com.biblioteca.dao.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.biblioteca.dao.LivroDAO;
import com.biblioteca.entitys.Livro;
import com.biblioteca.util.jpa.Transactional;

public class LivroService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LivroDAO livroDAO;
	
	@Transactional
	public void salvar(Livro livro) throws MensagemErroException{
		
		this.livroDAO.salvar(livro);
		
	}
	
	
}
