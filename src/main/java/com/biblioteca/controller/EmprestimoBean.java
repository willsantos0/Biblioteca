package com.biblioteca.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.biblioteca.dao.EmprestimoDAO;
import com.biblioteca.dao.LivroDAO;
import com.biblioteca.dao.service.EmprestimoService;
import com.biblioteca.dao.service.MensagemErroException;
import com.biblioteca.entitys.Emprestimo;
import com.biblioteca.entitys.Livro;
import com.biblioteca.util.jsf.FacesUtil;

@Named
@ViewScoped
public class EmprestimoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Emprestimo emprestimo;

	private Emprestimo emprestimoselecionado;

	private List<Emprestimo> emprestimos;

	private List<Livro> livros;

	private Livro livro;

	@Inject
	EmprestimoDAO emprestimoDAO;

	@Inject
	private EmprestimoService cadastroEmprestimoService;

	@Inject
	private LivroDAO livroDAO;


	// Quando a interface é carregada o postConstruct carrega o que estiver
	// dentro dele
	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.livros = livroDAO.buscarTodos();
		this.emprestimos = this.emprestimoDAO.buscarTodos();

	}

	public void limpar() {
		this.emprestimo = new Emprestimo();
	}

	public void salvar() {
		try {

			this.cadastroEmprestimoService.salvar(emprestimo);
			FacesUtil.addSuccessMessage("Empréstimo do livro '"+ emprestimo.getLivro_emprestado().getTitulo() +"' foi realizado com sucesso!");

		} catch (MensagemErroException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		refresh();
		this.limpar();
	}

	public void renovar() {
		try {
			
			this.emprestimoDAO.renovarEmprestimo(emprestimoselecionado);
			FacesUtil.addSuccessMessage(emprestimoselecionado.getLivro_emprestado().getTitulo() +  " foi renovado com sucesso!");

		} catch (MensagemErroException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		refresh();
	}

	public void devolver() {
		try {

			this.emprestimoDAO.devolverEmprestimo(emprestimoselecionado);
			FacesUtil.addSuccessMessage(emprestimoselecionado.getLivro_emprestado().getTitulo() +  " foi devolvido com sucesso!");

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
	public Emprestimo getEmprestimoselecionado() {
		return emprestimoselecionado;
	}

	public void setEmprestimoselecionado(Emprestimo emprestimoselecionado) {
		this.emprestimoselecionado = emprestimoselecionado;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
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

}
