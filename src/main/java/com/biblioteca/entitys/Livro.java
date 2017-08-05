
package com.biblioteca.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Livro{
    
    private String titulo;
    private int numero_chamada; 
    private String editora;
    private String genero;
    private String ediçao;
    private String autor;
    private int ano;
    private int qtd; // Quantidade de livros
    
    public Livro(){}
    
    
     public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getNumero_chamada() {
		return numero_chamada;
	}
	public void setNumero_chamada(int numero_chamada) {
		this.numero_chamada = numero_chamada;
	}

	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEdiçao() {
		return ediçao;
	}
	public void setEdiçao(String ediçao) {
		this.ediçao = ediçao;
	}

	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	
    @Column(name="quantidade_livros")
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((editora == null) ? 0 : editora.hashCode());
		result = prime * result + ((ediçao == null) ? 0 : ediçao.hashCode());
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		result = prime * result + numero_chamada;
		result = prime * result + qtd;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (ano != other.ano)
			return false;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (editora == null) {
			if (other.editora != null)
				return false;
		} else if (!editora.equals(other.editora))
			return false;
		if (ediçao == null) {
			if (other.ediçao != null)
				return false;
		} else if (!ediçao.equals(other.ediçao))
			return false;
		if (genero == null) {
			if (other.genero != null)
				return false;
		} else if (!genero.equals(other.genero))
			return false;
		if (numero_chamada != other.numero_chamada)
			return false;
		if (qtd != other.qtd)
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Livro [titulo=" + titulo + ", numero_chamada=" + numero_chamada
				+ ", editora=" + editora + ", genero=" + genero + ", ediçao="
				+ ediçao + ", autor=" + autor + ", ano=" + ano + ", qtd=" + qtd
				+ "]";
	}
	
	
	

}
