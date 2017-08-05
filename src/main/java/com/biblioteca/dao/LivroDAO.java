package com.biblioteca.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.biblioteca.dao.service.MensagemErroException;
import com.biblioteca.entitys.Livro;
import com.biblioteca.util.jpa.Transactional;

public class LivroDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;

	public void salvar(Livro livro) {
		em.merge(livro);
	}

	@SuppressWarnings("unchecked")
	public List<Livro> buscarTodos() {
		return em.createQuery("from Livro where quantidade_livros >= 0").getResultList();
	}

	@Transactional
	public void excluir(Livro livro) throws MensagemErroException {
		Livro livroTemp = em.find(Livro.class, livro.getNumero_chamada());

		if (livroTemp.getQtd() > 0) {
			livroTemp.setQtd(livroTemp.getQtd() - 1);
		} else {
			em.remove(livroTemp);
			em.flush();
		}
	}

	public Livro buscarPeloNumeroChamada(int numeroChamada) {
		return em.find(Livro.class, numeroChamada);
	}
}
