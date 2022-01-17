package br.com.sefaz.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import br.com.sefaz.entities.Usuario;
import br.com.sefaz.util.JPAUtil;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class UsuarioRepositoryTest {
	private Usuario usuario;
	private EntityManager entityManager;
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	public void setUp() {
		// Montando Cenario
		this.usuario = new Usuario(1L,"nome", "email", "senha", 222, "123456789", "Celular");
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
	}

	@AfterAll
	public void tearDown() {
		usuarioRepository.removeTodosUsuarios(); // limpar base de dados
		this.entityManager.close();
	}

	@Test
	@Order(1)
	public void testCadastrar() {
		// Execução
		Usuario usuario2 = this.usuarioRepository.buscaUsuarioPorId(Long.valueOf("1"));

		// Validação
		assertNotNull(usuario2);
	}

	@Test
	@Order(2)
	public void testBuscarUsuarioPorEmaileSenha() {
		// Execução
		Usuario usuario2 = this.usuarioRepository.buscarUsuarioPorEmaileSenha(this.usuario.getEmail(),
				this.usuario.getSenha());

		// Validação
		assertEquals(this.usuario, usuario2);
	}

	@Test
	@Order(3)
	public void testBuscaUsuarioPorId() {
		// Execução
		Usuario usuario2 = this.usuarioRepository.buscaUsuarioPorId(Long.valueOf("1"));

		// Validação
		assertEquals(this.usuario, usuario2);
	}

	@Test
	@Order(4)
	public void buscarUsuarios() {
		// Execução
		List<Usuario> list2 = this.usuarioRepository.buscarUsuarios();

		// Validação
		assertEquals(1, list2.size());
	}

	@Test
	@Order(5)
	public void testAtualizar() {
		Usuario user = usuarioRepository.buscaUsuarioPorId(1l);
		user.setEmail("3");

		// Execução
		this.usuarioRepository.atualizar(user);
		Usuario usuario2 = this.usuarioRepository.buscarUsuarioPorEmaileSenha(user.getEmail(), user.getSenha());

		// Validação
		assertEquals(user, usuario2);
	}

	@Test
	@Order(6)
	public void testRemove() {
		// Execução
		Usuario user = usuarioRepository.buscaUsuarioPorId(1l);
		this.usuarioRepository.remove(user);
		List<Usuario> usuarios = this.usuarioRepository.buscarUsuarios();

		// Validação
		assertEquals(0, usuarios.size());
	}

	@Test
	@Order(7)
	public void testRemoveTodosUsuarios() {
		// Execução
		usuarioRepository.cadastrar(new Usuario("nome", "email", "senha", 2222, "numero", "tipo"));
		this.usuarioRepository.removeTodosUsuarios();
		List<Usuario> usuarios = this.usuarioRepository.buscarUsuarios();

		// Validação
		assertEquals(0, usuarios.size());
	}
}
