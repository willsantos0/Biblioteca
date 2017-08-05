package com.biblioteca.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.biblioteca.dao.service.MensagemErroException;
import com.biblioteca.entitys.Emprestimo;
import com.biblioteca.entitys.Livro;
import com.biblioteca.entitys.Usuario;
import com.biblioteca.util.jpa.Transactional;

public class EmprestimoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuarioLogado;

	@Inject
	private EntityManager manager;

	@Inject
	private UsuarioDAO usuarios;

	@Inject
	private LivroDAO livros;
	
 
	public Emprestimo buscarNumeroEmprestimo(int numeroEmprestimo) {
		return manager.find(Emprestimo.class, numeroEmprestimo);
	}

	public void salvar(Emprestimo emprestimo) {
		manager.merge(emprestimo);
	}

	@SuppressWarnings("unchecked")
	public List<Emprestimo> buscarTodos() {
		usuarioLogado = usuarios.retornaUsuarioLogado();
		return manager.createQuery("from Emprestimo where codigo_usuario = :usuarioLogado and status_livro = 1")
				      .setParameter("usuarioLogado", usuarioLogado).getResultList();
	}
	
	
	@Transactional
	public void renovarEmprestimo(Emprestimo emprestimo)
			throws MensagemErroException {

		usuarioLogado = usuarios.retornaUsuarioLogado();

		if (usuarioLogado == null) {
			throw new MensagemErroException("Usuario não Logado");
		}

		int numero_chamada = emprestimo.getLivro_emprestado().getNumero_chamada();
		Livro livro = this.livros.buscarPeloNumeroChamada(numero_chamada);
		
		if(emprestimo.getLivro_emprestado().getNumero_chamada() == livro.getNumero_chamada() && usuarioLogado.getId() == emprestimo.getUsuario().getId()){
			if(emprestimo.getQtdRenovacoes()>= 7)
				throw new MensagemErroException("Quantidade de renovações excedida");
			
			emprestimo.setQtdRenovacoes(emprestimo.getQtdRenovacoes()+1);
			
		}else
			throw new MensagemErroException("Você não pode renovar este livro");
		
				Date dataEmprestimo = new Date();

				// Data que o usuario deverá devolver o livro(7 dias após o
				// emprestimo).
				Calendar data_devolucao = new GregorianCalendar();

				// Soma a data do emprestimo com os 7 dias estipulados.
				data_devolucao.add(Calendar.DAY_OF_MONTH, 7);

				emprestimo.setData_emprestimo(dataEmprestimo);
				emprestimo.setData_devoluçao(data_devolucao.getTime());

				// emprestimo.setUsuario(usuarioLogado);

				salvar(emprestimo);
		
	}
	
	
	@Transactional
	public void devolverEmprestimo(Emprestimo emprestimo)
			throws MensagemErroException {
		try {
		//emprestimo = buscarNumeroEmprestimo(emprestimo
		//		.getNumero_emprestimo());

			usuarioLogado = usuarios.retornaUsuarioLogado();

			int numero_chamada = emprestimo.getLivro_emprestado()
					.getNumero_chamada();
			Livro livro = this.livros.buscarPeloNumeroChamada(numero_chamada);

			if (emprestimo.getLivro_emprestado().getNumero_chamada() == livro
					.getNumero_chamada()
					&& usuarioLogado.getId() == emprestimo.getUsuario().getId()) {
				livro.setQtd(livro.getQtd() + 1);
				usuarioLogado.setQtd_emprestimos_usuario(usuarioLogado
						.getQtd_emprestimos_usuario() - 1);
			} else
				throw new MensagemErroException(
						"Você não tem permissão para devolver este livro");
			
			double multa = multa(emprestimo);
			
			usuarioLogado.setMulta(multa);
			
			emprestimo.setStatus_livro(false);
				
			Date diaDevolucao = new Date();
			emprestimo.setDia_devoluçao(diaDevolucao);

			salvar(emprestimo);

		} catch (PersistenceException e) {
			throw new MensagemErroException(
					"Este empréstimo não pode ser excluido");
		}
	}
	
	public double multa(Emprestimo emprestimo){
		
		usuarioLogado = usuarios.retornaUsuarioLogado();
		
		emprestimo = buscarNumeroEmprestimo(emprestimo
				.getNumero_emprestimo());
		
		double multa = 0.0;
		Calendar dataDevolucao = new GregorianCalendar();
		Calendar dataAtual= new GregorianCalendar();
		
		dataDevolucao.setTime(emprestimo.getData_devoluçao());
		
		long m = ((dataAtual.getTimeInMillis() - dataDevolucao.getTimeInMillis()) / (24*60*60*1000));    

		if(dataAtual.after(dataDevolucao))
			multa = m;
	 
		usuarioLogado.setMulta(multa);
		
		return multa;
	}
	
}
