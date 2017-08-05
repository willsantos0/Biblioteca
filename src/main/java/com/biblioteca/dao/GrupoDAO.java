package com.biblioteca.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.biblioteca.dao.service.MensagemErroException;
import com.biblioteca.entitys.Grupo;
import com.biblioteca.util.jpa.Transactional;

public class GrupoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Grupo buscarPeloId(Long id) {
		return manager.find(Grupo.class, id);
	}
	
	public void salvar(Grupo usuario) {
		manager.merge(usuario);
	}

	@SuppressWarnings("unchecked")
	public List<Grupo> buscarTodos() {
		return manager.createQuery("from Grupo").getResultList();
	}
	
	@Transactional
	public void excluir(Grupo usuario) throws MensagemErroException {
		usuario = buscarPeloId(usuario.getId());
		try {
			manager.remove(usuario);
			manager.flush();
		} catch (PersistenceException e) {
			throw new MensagemErroException("Grupo não pode ser excluído.");
		}
	}
}
