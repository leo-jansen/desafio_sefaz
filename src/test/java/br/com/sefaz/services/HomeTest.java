package br.com.sefaz.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

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
public class HomeTest {
	private EntityManager entityManager;
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	public void setUp() {
		// Montando Cenario
		this.entityManager = JPAUtil.getEntityManager();
		this.usuarioRepository = new UsuarioRepository(entityManager);
		usuarioRepository.cadastrar(new Usuario("nome", "email", "senha", 22, "numero", "tipo"));
		this.entityManager.close();
	}
	
	@AfterAll
	public void tearDown() {
		this.entityManager = JPAUtil.getEntityManager();
		this.usuarioRepository = new UsuarioRepository(entityManager);
		usuarioRepository.removeTodosUsuarios(); //limpar base de dados
		this.entityManager.close();
	}

	@Test
	public void testExecuta() throws ServletException, IOException {
		// Montando cenario
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		Home home = new Home();

		// Execução
		String retorno = home.executa(request, response);

		// Validação
		assertEquals("forward:home.jsp", retorno);
	}
}
