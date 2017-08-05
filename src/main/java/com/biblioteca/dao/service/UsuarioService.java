package com.biblioteca.dao.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.biblioteca.dao.UsuarioDAO;
import com.biblioteca.entitys.Usuario;
import com.biblioteca.util.jpa.Transactional;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Transactional
	public void salvar(Usuario usuario) throws MensagemErroException{
		
		this.usuarioDAO.salvar(usuario);
		
	}
	
	public Usuario informacoesUsuarioLogado(){
		return usuarioDAO.retornaUsuarioLogado();
	}
	
	
}
