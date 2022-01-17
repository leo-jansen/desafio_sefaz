package br.com.sefaz.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import br.com.sefaz.entities.Usuario;
import br.com.sefaz.repositories.UsuarioRepository;
import br.com.sefaz.util.JPAUtil;

@TestInstance(Lifecycle.PER_CLASS)
public class ValidacaoTest {
	private HttpServletRequest request;
	private HttpServletResponse response;

	@BeforeAll
	public void setUp() {
		this.request = mock(HttpServletRequest.class);
		this.response = mock(HttpServletResponse.class);
	}

	@AfterEach
	public void tearDown() {
		EntityManager entityManager = JPAUtil.getEntityManager();
		UsuarioRepository usuarioRepository = new UsuarioRepository(entityManager);
		usuarioRepository.removeTodosUsuarios(); // limpar base de dados
		entityManager.close();
	}


	/**
	 * teste email e senha OK
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testExecuta() throws ServletException, IOException {
		// Montando cenario
		EntityManager entityManager = JPAUtil.getEntityManager();
		UsuarioRepository usuarioRepository = new UsuarioRepository(entityManager);
		usuarioRepository.cadastrar(new Usuario("nome", "email", "senha", 222, "numero", "tipo"));
		entityManager.close();
		when(request.getParameter("email")).thenReturn("email");
		when(request.getParameter("senha")).thenReturn("senha");
		when(request.getSession()).thenReturn(mock(HttpSession.class));

		Validacao validacao = new Validacao();

		// Execucao
		String retorno = validacao.executa(request, response);

		// Validação
		assertEquals("redirect:/sefaz/home", retorno);
	}

	/**
	 * teste email e senha falho
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	public void testExecutaFail() throws ServletException, IOException {
		// Montando cenario
		when(request.getParameter("email")).thenReturn("email");
		when(request.getParameter("senha")).thenReturn("senha");

		Validacao validacao = new Validacao();

		// Execucao
		String retorno = validacao.executa(request, response);

		// Validação
		assertEquals("forward:login.jsp", retorno);
	}
}
