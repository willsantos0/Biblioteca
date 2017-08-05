package com.biblioteca.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.biblioteca.dao.GrupoDAO;
import com.biblioteca.dao.service.GrupoService;
import com.biblioteca.dao.service.MensagemErroException;
import com.biblioteca.entitys.Grupo;
import com.biblioteca.util.jsf.FacesUtil;

@Named
@ViewScoped
public class GrupoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Grupo grupo;

	private Grupo grupoSelecionado;

	@Inject
	GrupoDAO grupoDAO;

	@Inject
	private GrupoService cadastroGrupoService;

	private List<Grupo> grupos;

	@PostConstruct
	public void init() {
		this.grupos = new ArrayList<>();
		this.grupos = grupoDAO.buscarTodos();
	}

	public void limpar() {
		this.grupo = new Grupo();

	}

	public GrupoBean() {
		this.limpar();
	}

	public void salvar() {
		try {
			this.cadastroGrupoService.salvar(grupo);
			FacesUtil.addSuccessMessage("Grupo salvo com sucesso!");
		} catch (MensagemErroException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

		this.limpar();
	}

	public void excluir() {
		try {
			grupoDAO.excluir(grupoSelecionado);
			this.grupos.remove(grupoSelecionado);
			FacesUtil.addSuccessMessage("Grupo " + grupoSelecionado.getDescricao() + " excluído com sucesso.");
			
		} catch (MensagemErroException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	
	// Getters e Setters
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}

}
