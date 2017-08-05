
package com.biblioteca.entitys;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Emprestimo {
  
  private int numero_emprestimo;
  private Date data_emprestimo;
  private Date data_devoluçao;
  private Date dia_devoluçao;
  private Livro livro_emprestado;
  private boolean status_livro;
  private Usuario usuario;
  private int qtdRenovacoes;
    
    public Emprestimo(){}

    
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getNumero_emprestimo() {
		return numero_emprestimo;
	}
	public void setNumero_emprestimo(int numero_emprestimo) {
		this.numero_emprestimo = numero_emprestimo;
	}
	
	public Date getData_emprestimo() {
		return data_emprestimo;
	}
	public void setData_emprestimo(Date data_emprestimo) {
		this.data_emprestimo = data_emprestimo;
	}
	
	public Date getData_devoluçao() {
		return data_devoluçao;
	}
	public void setData_devoluçao(Date data_devoluçao) {
		this.data_devoluçao = data_devoluçao;
	}
	
	public Date getDia_devoluçao() {
		return dia_devoluçao;
	}
	public void setDia_devoluçao(Date dia_devoluçao) {
		this.dia_devoluçao = dia_devoluçao;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="livro_emprestado")
	public Livro getLivro_emprestado() {
		return livro_emprestado;
	}
	public void setLivro_emprestado(Livro livro_emprestado) {
		this.livro_emprestado = livro_emprestado;
	}

	public boolean isStatus_livro() {
		return status_livro;
	}
	public void setStatus_livro(boolean status_livro) {
		this.status_livro = status_livro;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="codigo_usuario")
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public int getQtdRenovacoes() {
		return qtdRenovacoes;
	}
	public void setQtdRenovacoes(int qtdRenovacoes) {
		this.qtdRenovacoes = qtdRenovacoes;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((data_devoluçao == null) ? 0 : data_devoluçao.hashCode());
		result = prime * result
				+ ((data_emprestimo == null) ? 0 : data_emprestimo.hashCode());
		result = prime * result
				+ ((dia_devoluçao == null) ? 0 : dia_devoluçao.hashCode());
		result = prime
				* result
				+ ((livro_emprestado == null) ? 0 : livro_emprestado.hashCode());
		result = prime * result + numero_emprestimo;
		result = prime * result + qtdRenovacoes;
		result = prime * result + (status_livro ? 1231 : 1237);
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Emprestimo other = (Emprestimo) obj;
		if (data_devoluçao == null) {
			if (other.data_devoluçao != null)
				return false;
		} else if (!data_devoluçao.equals(other.data_devoluçao))
			return false;
		if (data_emprestimo == null) {
			if (other.data_emprestimo != null)
				return false;
		} else if (!data_emprestimo.equals(other.data_emprestimo))
			return false;
		if (dia_devoluçao == null) {
			if (other.dia_devoluçao != null)
				return false;
		} else if (!dia_devoluçao.equals(other.dia_devoluçao))
			return false;
		if (livro_emprestado == null) {
			if (other.livro_emprestado != null)
				return false;
		} else if (!livro_emprestado.equals(other.livro_emprestado))
			return false;
		if (numero_emprestimo != other.numero_emprestimo)
			return false;
		if (qtdRenovacoes != other.qtdRenovacoes)
			return false;
		if (status_livro != other.status_livro)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Emprestimo [numero_emprestimo=" + numero_emprestimo
				+ ", data_emprestimo=" + data_emprestimo + ", data_devoluçao="
				+ data_devoluçao + ", dia_devoluçao=" + dia_devoluçao
				+ ", livro_emprestado=" + livro_emprestado + ", status_livro="
				+ status_livro + ", usuario=" + usuario + ", qtdRenovacoes="
				+ qtdRenovacoes + "]";
	}


    
}
