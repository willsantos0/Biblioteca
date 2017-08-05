package com.biblioteca.security;

import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Named
@RequestScoped
public class Seguranca {
	
	@Inject
	private ExternalContext externalContext;

	public String getNomeUsuario() {
		String nome = null;

		UsuarioSistema usuarioLogado = getUsuarioLogado();
		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getNome();
		}
		return nome;
	}

	private UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;

		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();

		if (auth != null && auth.getPrincipal() != null)
			usuario = (UsuarioSistema) auth.getPrincipal();

		return usuario;
	}

	public Long getIdUsuario() {
		Long id = null;

		UsuarioSistema usuarioLogado = getUsuarioLogado();

		if (usuarioLogado != null) {
			id = usuarioLogado.getUsuario().getId();
		}
		return id;
	}
	
	public boolean isMostrarPaginas(){
		return externalContext.isUserInRole("ADMINISTRADOR");
	}
	
}
