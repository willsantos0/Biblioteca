package com.biblioteca.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.biblioteca.dao.GrupoDAO;
import com.biblioteca.entitys.Grupo;
import com.biblioteca.util.cdi.CDIServiceLocator;

@FacesConverter(forClass= Grupo.class)
public class GrupoConverter1 implements Converter {

	private GrupoDAO grupoDAO;

	public GrupoConverter1() {
		this.grupoDAO = CDIServiceLocator.getBean(GrupoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Grupo retorno = null;
		
		if (value != null) {
			retorno = this.grupoDAO.buscarPeloId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((Grupo) value).getId();
			String retorno = (id == null ? null : id.toString());
			
			return retorno;
		}
		
		return "";
	}

}