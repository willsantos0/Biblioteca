package com.biblioteca.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.biblioteca.dao.EmprestimoDAO;
import com.biblioteca.dao.GrupoDAO;
import com.biblioteca.dao.UsuarioDAO;
import com.biblioteca.dao.service.MensagemErroException;
import com.biblioteca.dao.service.UsuarioService;
import com.biblioteca.entitys.Emprestimo;
import com.biblioteca.entitys.Grupo;
import com.biblioteca.entitys.Usuario;
import com.biblioteca.util.jsf.FacesUtil;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	
	private Usuario usuarioSelecionado;

	private List<Emprestimo> emprestimos;
	
	private List<Grupo> grupos;
	
	private List<Usuario> usuarios;
	
	@Inject
	UsuarioDAO usuarioDAO;
	
	@Inject
	private UsuarioService cadastroUsuarioService;
	
	@Inject
	private GrupoDAO grupoDAO;
	
	@Inject
	private EmprestimoDAO emprestimoDAO;
	
	
	
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.usuarios = usuarioDAO.buscarTodos();
		this.grupos = grupoDAO.buscarTodos();
		this.emprestimos = this.emprestimoDAO.buscarTodos();
	}
	
	public void limpar() {
		this.usuario = new Usuario();
		this.usuario.setGrupos(new ArrayList<Grupo>());
	}
	
	
	public void salvar() {
		try {
			this.cadastroUsuarioService.salvar(usuario);
			FacesUtil.addSuccessMessage("Usuário salvo com sucesso!");
			
		} catch (MensagemErroException e) {
			FacesUtil.addErrorMessage(e.getMessage());
			
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro desconhecido. Contatar o administrador");
		}
		
		this.limpar();
	}
	
	
	public void excluir() {
		try {
			usuarioDAO.excluir(usuarioSelecionado);
			this.usuarios.remove(usuarioSelecionado);
			FacesUtil.addSuccessMessage("Usuário " + usuarioSelecionado.getnomeUsuario() + " excluído com sucesso.");
		} catch (MensagemErroException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	

	public Usuario informacoes(){
		usuario = cadastroUsuarioService.informacoesUsuarioLogado();
		return usuario;
	
	}
	
	
	
	// Getters e Setters
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}
	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}
	
	
	
}
