package com.fatec.sce.sce;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import com.fatec.sce.model.DAOFactory;
import com.fatec.sce.model.ILivroDAO;
import com.fatec.sce.model.Livro;

public class UC02ConsultarLivro {
	@Test
	public void CT01CadastrarLivro_com_sucesso() {
		// cenario
		Livro umLivro = ObtemLivro.comDadosValidos();
		Livro resultadoObtido = null;
		DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ILivroDAO livroDAO = mySQLFactory.getLivroDAO();
		// acao
		livroDAO.adiciona(umLivro);
		resultadoObtido = livroDAO.consulta(umLivro.getIsbn());
		// verificacao
		assertTrue(resultadoObtido.equals(umLivro));
		livroDAO.exclui(umLivro.getIsbn());
	}
}