package com.biblioteca.dao;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.biblioteca.dao.service.MensagemErroException;
import com.biblioteca.entitys.Usuario;
import com.biblioteca.util.jpa.Transactional;

public class UsuarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Usuario porId(Long id) {
		return this.manager.find(Usuario.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> buscarTodos() {
		return manager.createQuery("from Usuario").getResultList();
	}
	
	
	public Usuario porUser(String user){
		Usuario usuario = null;
		
		try{
		usuario = this.manager.createQuery("from Usuario where lower(nomeUsuario) = :user", Usuario.class)
					.setParameter("user", user.toLowerCase()).getSingleResult();
		}catch(NoResultException e){
			// Nenhum usuario encontrado com o user informado
		}
		return usuario;
	}
	

	public void salvar(Usuario usuario) {
		manager.merge(usuario);
		
	}
	
	@Transactional
	public void excluir(Usuario usuario) throws MensagemErroException {
		usuario = porId(usuario.getId());
		try {
			manager.remove(usuario);
			manager.flush();
		} catch (PersistenceException e) {
			throw new MensagemErroException("Usuario não pode ser excluído.");
		}
	}
	
	
	public Usuario retornaUsuarioLogado() {
		
		Usuario usuarioLogado = null;
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();
		usuarioLogado = porUser(login);
		return usuarioLogado;
	}
	
}