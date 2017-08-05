package com.biblioteca.controller;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.server.ServerCloneException;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.biblioteca.util.jsf.FacesUtil;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	SecurityContext context = SecurityContextHolder.getContext();
	Authentication authentication = context.getAuthentication();
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private HttpServletRequest request;
	
	@Inject
	private HttpServletResponse response;
	
	private String nomeUsuario;

	public void preRender() {
		if ("true".equals(request.getParameter("invalid"))) {
			FacesUtil.addErrorMessage("Usuário ou senha inválido!");
		}
	}
	
	public void login() throws ServerCloneException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(request, response);
		
		facesContext.responseComplete();
	}
	
	public String getnomeUsuario() {
		return nomeUsuario;
	}

	public void setnomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
}