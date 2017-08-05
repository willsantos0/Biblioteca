package com.biblioteca.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.biblioteca.dao.UsuarioDAO;
import com.biblioteca.entitys.Grupo;
import com.biblioteca.entitys.Usuario;
import com.biblioteca.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
		UsuarioDAO usuarios = CDIServiceLocator.getBean(UsuarioDAO.class);
		Usuario usuario = usuarios.porUser(user);
		UsuarioSistema userS = null;
		
		if(usuario != null){
			userS = new UsuarioSistema(usuario, getauthotities(usuario)); 
		}
		
		return userS;
	}

	private Collection<? extends GrantedAuthority> getauthotities(Usuario usuario) {
		List<SimpleGrantedAuthority> authotities = new ArrayList<>();
		
		for(Grupo grupo : usuario.getGrupos()){
			authotities.add(new SimpleGrantedAuthority(grupo.getDescricao().toUpperCase()));
		}
		
		return authotities;
	}
}
