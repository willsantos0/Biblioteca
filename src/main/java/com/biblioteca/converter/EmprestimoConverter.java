package com.biblioteca.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.biblioteca.dao.EmprestimoDAO;
import com.biblioteca.entitys.Emprestimo;
import com.biblioteca.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Emprestimo.class)
public class EmprestimoConverter implements Converter {

	private EmprestimoDAO emprestimoDAO;
	
	public EmprestimoConverter() {
		this.emprestimoDAO = CDIServiceLocator.getBean(EmprestimoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Emprestimo retorno = null;

		if (value != null) {
			retorno = this.emprestimoDAO.buscarNumeroEmprestimo(new Integer(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
	
		Emprestimo emprestimo = (Emprestimo) value;
		
		try{
			return String.valueOf(emprestimo.getNumero_emprestimo());
		}catch(Exception e){
			return null;
		}
	}
	
	

}