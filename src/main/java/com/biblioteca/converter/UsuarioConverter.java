package com.biblioteca.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.biblioteca.dao.UsuarioDAO;
import com.biblioteca.entitys.Usuario;
import com.biblioteca.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	// @Inject
	private UsuarioDAO usuarios;

	public UsuarioConverter() {
		this.usuarios = (UsuarioDAO) CDIServiceLocator
				.getBean(UsuarioDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Usuario retorno = null;

		if (value != null) {
			retorno = this.usuarios.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Long id = ((Usuario) value).getId();
			String retorno = (id == null ? null : id.toString());

			return retorno;
		}
		return "";
	}

}