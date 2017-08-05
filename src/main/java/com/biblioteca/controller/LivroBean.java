package com.biblioteca.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.biblioteca.dao.LivroDAO;
import com.biblioteca.dao.service.LivroService;
import com.biblioteca.dao.service.MensagemErroException;
import com.biblioteca.entitys.Livro;
import com.biblioteca.util.jsf.FacesUtil;

@Named
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	LivroDAO livroDAO;

	private List<Livro> livros;

	@Inject
	private LivroService cadastroLivroService;

	private Livro livro;

	private Livro livroSelecionado;
	
	@PostConstruct
	public void init() {
		livros = new ArrayList<>();
		livros = livroDAO.buscarTodos();
		this.limpar();
	}

	public void limpar() {
		 this.livro = new Livro();
	}

	public void salvar() throws Exception {
		try {
			
			this.cadastroLivroService.salvar(livro);
			FacesUtil.addSuccessMessage("Livro salvo com sucesso");
			
			this.limpar();
		} catch (MensagemErroException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		refresh();
	}

	public void excluir() {
		try {
			
			livroDAO.excluir(livroSelecionado);
			this.livros.remove(livroSelecionado);
			
			FacesUtil.addSuccessMessage(livroSelecionado + " excluido com Sucesso");
			
		} catch (MensagemErroException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

		refresh();
	}


	public void refresh() {
		FacesContext context = FacesContext.getCurrentInstance();
		javax.faces.application.Application application = context.getApplication();
		javax.faces.application.ViewHandler viewHandler = application.getViewHandler();
		UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
		context.setViewRoot(viewRoot);
		context.renderResponse();
	}
    
	
	
	// Getters e Setters
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Livro getLivroSelecionado() {
		return livroSelecionado;
	}

	public void setLivroSelecionado(Livro livroSelecionado) {
		this.livroSelecionado = livroSelecionado;
	}

}
