package br.com.sefaz.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import br.com.sefaz.entities.Usuario;
import br.com.sefaz.repositories.UsuarioRepository;
import br.com.sefaz.util.JPAUtil;

@TestInstance(Lifecycle.PER_CLASS)
public class CadastrarUsuarioTest {
	private EntityManager entityManager;
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	public void setUp() {
		// Montando Cenario
		this.entityManager = JPAUtil.getEntityManager();
		this.usuarioRepository = new UsuarioRepository(entityManager);
		this.entityManager.close();
	}
	
	@AfterAll
	public void tearDown() {
		this.entityManager = JPAUtil.getEntityManager();
		this.usuarioRepository = new UsuarioRepository(entityManager);
		usuarioRepository.removeTodosUsuarios(); //limpar base de dados
		this.entityManager.close();
	}
	/**
	 * Teste com um email não utilizado
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testExecutaValido() throws ServletException, IOException {
		// Montando cenario
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		when(request.getParameter("nome")).thenReturn("nome");
		when(request.getParameter("email")).thenReturn("email");
		when(request.getParameter("senha")).thenReturn("senha");
		when(request.getParameter("ddd")).thenReturn("21");
		when(request.getParameter("numero")).thenReturn("123456789");
		when(request.getParameter("tipo")).thenReturn("Celular");

		CadastrarUsuario cadastrarUsuario = new CadastrarUsuario();

		// Execução
		String retorno = cadastrarUsuario.executa(request, response);
		

		// Validação
		assertEquals("redirect:/sefaz/home", retorno);
	}

	/**
	 * Teste com email já utilizado
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testExecutaInvalido() throws ServletException, IOException {
		// Montando cenario
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		this.entityManager = JPAUtil.getEntityManager();
		this.usuarioRepository = new UsuarioRepository(entityManager);
		this.usuarioRepository.cadastrar(new Usuario("nome1", "email1", "senha", 2222, "numero", "tipo"));
		this.entityManager.close();

		when(request.getParameter("nome")).thenReturn("nome");
		when(request.getParameter("email")).thenReturn("email");
		when(request.getParameter("senha")).thenReturn("senha");
		when(request.getParameter("ddd")).thenReturn("21");
		when(request.getParameter("numero")).thenReturn("123456789");
		when(request.getParameter("tipo")).thenReturn("Celular");

		CadastrarUsuario cadastrarUsuario = new CadastrarUsuario();

		// Execução
		String retorno = cadastrarUsuario.executa(request, response);
		

		// Validação
		assertEquals("forward:usuarioForm.jsp", retorno);
	}

}
