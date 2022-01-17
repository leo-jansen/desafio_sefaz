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

import br.com.sefaz.repositories.UsuarioRepository;
import br.com.sefaz.util.JPAUtil;

@TestInstance(Lifecycle.PER_CLASS)
public class AlterarTest {
	private EntityManager entityManager;
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	public void setUp() {
		// Montando Cenario
		this.entityManager = JPAUtil.getEntityManager();
		this.usuarioRepository = new UsuarioRepository(entityManager);
		String query = "insert into tb_usuarios (id, nome, email, senha, ddd, numero, tipo) values (:id,:nome,:email,:senha,:ddd,:numero,:tipo)";
		this.entityManager.getTransaction().begin();
		this.entityManager.createNativeQuery(query)
				.setParameter("id", 1L)
				.setParameter("nome", "nome")
				.setParameter("email", "email")
				.setParameter("senha", "senha")
				.setParameter("ddd", 222)
				.setParameter("numero", "123456789")
				.setParameter("tipo", "Celular")
				.executeUpdate();
		this.entityManager.getTransaction().commit();
		this.entityManager.close();
	}

	@AfterAll
	public void tearDown() {
		this.entityManager = JPAUtil.getEntityManager();
		this.usuarioRepository = new UsuarioRepository(entityManager);
		usuarioRepository.removeTodosUsuarios(); // limpar base de dados
		this.entityManager.close();
	}

	@Test
	public void testExecuta() throws ServletException, IOException {
		// Montando cenario
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		when(request.getParameter("id")).thenReturn("1");
		when(request.getParameter("nome")).thenReturn("nome");
		when(request.getParameter("email")).thenReturn("email");
		when(request.getParameter("senha")).thenReturn("senha");
		when(request.getParameter("ddd")).thenReturn("21");
		when(request.getParameter("numero")).thenReturn("123456789");
		when(request.getParameter("tipo")).thenReturn("Celular");

		Alterar alterar = new Alterar();

		// Execução
		String retorno = alterar.executa(request, response);

		// Validação
		assertEquals("redirect:/sefaz/home", retorno);
	}
}
