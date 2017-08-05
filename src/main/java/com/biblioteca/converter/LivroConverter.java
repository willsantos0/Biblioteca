package com.biblioteca.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.biblioteca.dao.LivroDAO;
import com.biblioteca.entitys.Livro;
import com.biblioteca.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Livro.class)
public class LivroConverter implements Converter {

	private LivroDAO livroDAO;
	
	public LivroConverter() {
		this.livroDAO = CDIServiceLocator.getBean(LivroDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Livro retorno = null;

		if (value != null) {
			retorno = this.livroDAO.buscarPeloNumeroChamada(new Integer(value));
		}											

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Livro livro = (Livro) value;
		
		try{
			return String.valueOf(livro.getNumero_chamada());
		}catch(Exception e){
			return null;
		}
		
	}

}
