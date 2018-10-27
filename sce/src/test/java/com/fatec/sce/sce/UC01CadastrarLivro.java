package com.fatec.sce.sce;

import static org.junit.Assert.*;
import org.junit.Test;
import com.fatec.sce.model.DAOFactory;
import com.fatec.sce.model.ILivroDAO;
import com.fatec.sce.model.Livro;

public class UC01CadastrarLivro {

	@Test
	public void CT01CadastrarLivroComDadosValidos() {
		try {
			// cenario
			Livro umLivro = new Livro();
			// acao
			umLivro.setIsbn("121212");
			umLivro.setTitulo("Engenharia de Softwar");
			umLivro.setAutor("Pressman");
		} catch (RuntimeException e) {
			// verificacao
			fail("nao deve falhar");
		}
	}

	@Test
	public void CT02CadastrarLivroComISBNBranco() {
		try {
			// cenario
			Livro umLivro = new Livro();
			// acao
			umLivro.setIsbn("");
			umLivro.setTitulo("Engenharia de Softwar");
			umLivro.setAutor("Pressman");
		} catch (RuntimeException e) {
			// verificacao
			assertEquals("ISBN invalido", e.getMessage());
		}
	}

//	@Test
//	public void CT03CadastrarLivro_com_sucesso() {
////		// cenario
//		Livro umLivro = ObtemLivro.comDadosValidos();
//		DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
//		ILivroDAO livroDAO = mySQLFactory.getLivroDAO();
////		// acao
//		int codigoRetorno = livroDAO.adiciona(umLivro);
//		// verificacao
//		assertEquals(1, codigoRetorno);
//		livroDAO.exclui(umLivro.getIsbn());
//	}

	@Test
	public void CT04CadastrarLivroComISBNJaCadastrado() {
		// cenario
		Livro umLivro = ObtemLivro.comDadosValidos();
		Livro novoLivro = null;
		DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ILivroDAO livroDAO = mySQLFactory.getLivroDAO();
		try {
			// acao
			livroDAO.adiciona(umLivro);
			novoLivro = livroDAO.consulta(umLivro.getIsbn());
			// verificacao
			livroDAO.adiciona(novoLivro);
		} catch (RuntimeException e) {
			assertEquals("com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry '121212' for key 'PRIMARY'", e.getMessage());
		}
		livroDAO.exclui(umLivro.getIsbn());
	}
	
	@Test
	public void CT04CadastrarLivroComISBNNulo() {
		try {
			// cenario
			Livro umLivro = ObtemLivro.comISBNInvalido_nulo();
			DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			ILivroDAO livroDAO = mySQLFactory.getLivroDAO();
			// acao
			livroDAO.adiciona(umLivro);
			// verificacao
			livroDAO.exclui(umLivro.getIsbn());
		} catch (Throwable e) {
			assertEquals("ISBN invalido", e.getMessage());
		}
	}
}
