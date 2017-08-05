package com.biblioteca.entitys;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String cpf;
	private String nome;
	private String endereço;
	private String cep;
	private String nomeUsuario;
	private String senha;
	private int qtd_emprestimos_usuario;
	private List<Grupo> grupos;
	private List<Emprestimo> emprestimos;
	private double multa;
	
	public Usuario(){}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(unique=true)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(nullable = false, length = 80)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereço() {
		return endereço;
	}

	public void setEndereço(String endereço) {
		this.endereço = endereço;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Column(nullable = false, unique = true, length = 50)
	public String getnomeUsuario() {
		return nomeUsuario;
	}

	public void setnomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	@Column(nullable = false, length = 20)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getQtd_emprestimos_usuario() {
		return qtd_emprestimos_usuario;
	}

	public void setQtd_emprestimos_usuario(int qtd_emprestimos_usuario) {
		this.qtd_emprestimos_usuario = qtd_emprestimos_usuario;
	}
	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	@OneToMany(mappedBy="usuario")
	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}

	public double getMulta() {
		return multa;
	}
	public void setMulta(double multa) {
		this.multa = multa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result
				+ ((emprestimos == null) ? 0 : emprestimos.hashCode());
		result = prime * result
				+ ((endereço == null) ? 0 : endereço.hashCode());
		result = prime * result + ((grupos == null) ? 0 : grupos.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(multa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((nomeUsuario == null) ? 0 : nomeUsuario.hashCode());
		result = prime * result + qtd_emprestimos_usuario;
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Usuario other = (Usuario) obj;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (emprestimos == null) {
			if (other.emprestimos != null)
				return false;
		} else if (!emprestimos.equals(other.emprestimos))
			return false;
		if (endereço == null) {
			if (other.endereço != null)
				return false;
		} else if (!endereço.equals(other.endereço))
			return false;
		if (grupos == null) {
			if (other.grupos != null)
				return false;
		} else if (!grupos.equals(other.grupos))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(multa) != Double
				.doubleToLongBits(other.multa))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomeUsuario == null) {
			if (other.nomeUsuario != null)
				return false;
		} else if (!nomeUsuario.equals(other.nomeUsuario))
			return false;
		if (qtd_emprestimos_usuario != other.qtd_emprestimos_usuario)
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

	
	
	
}
