package com.biblioteca.dao.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import com.biblioteca.dao.EmprestimoDAO;
import com.biblioteca.dao.LivroDAO;
import com.biblioteca.dao.UsuarioDAO;
import com.biblioteca.entitys.Emprestimo;
import com.biblioteca.entitys.Livro;
import com.biblioteca.entitys.Usuario;
import com.biblioteca.util.jpa.Transactional;

public class EmprestimoService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private EmprestimoDAO emprestimoDAO;

	@Inject
	private UsuarioDAO usuarios;

	private Usuario usuarioLogado;

	@Inject
	private LivroDAO livros;

	private List<Emprestimo> emprestimos;

	@Transactional
	public void salvar(Emprestimo emprestimo) throws MensagemErroException {

		if (emprestimo.getLivro_emprestado() == null)
			throw new MensagemErroException("Livro não selecionado");
		
			usuarioLogado = usuarios.retornaUsuarioLogado();
		
		if (usuarioLogado == null) {
			throw new MensagemErroException("Usuario não Logado");
		}

		if (emprestimo.getLivro_emprestado().getQtd() == 0) {
			throw new MensagemErroException("Este livro não está disponível para empréstimo");
		}

		if (usuarioLogado.getQtd_emprestimos_usuario() >= 5)
			throw new MensagemErroException("Quantidade de livros por usuario excedido");

		usuarioLogado.setQtd_emprestimos_usuario(usuarioLogado.getQtd_emprestimos_usuario() + 1);

		int numero_chamada = emprestimo.getLivro_emprestado().getNumero_chamada();
		Livro livro = this.livros.buscarPeloNumeroChamada(numero_chamada);

		this.emprestimos = this.emprestimoDAO.buscarTodos();

		for (int i = 0; i < this.emprestimos.size(); i++) {
			if (this.emprestimos.get(i).getUsuario().equals(usuarioLogado)
					&& this.emprestimos.get(i).getLivro_emprestado()
							.equals(livro))
				throw new MensagemErroException("Você ja possui este livro");

		}

		livro.setQtd(livro.getQtd() - 1);

		Date dataEmprestimo = new Date();

		// Data que o usuario deverá devolver o livro(7 dias após o emprestimo).
		Calendar data_devolucao = new GregorianCalendar();

		// Soma a data do emprestimo com os 7 dias estipulados.
		data_devolucao.add(Calendar.DAY_OF_MONTH, 7);

		emprestimo.setData_emprestimo(dataEmprestimo);
		emprestimo.setData_devoluçao(data_devolucao.getTime());
		emprestimo.setStatus_livro(true);

		emprestimo.setUsuario(usuarioLogado);

		this.emprestimoDAO.salvar(emprestimo);

	}

}
