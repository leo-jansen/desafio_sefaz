package br.com.sefaz.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.sefaz.entities.Usuario;

public class UsuarioRepository {
	private EntityManager entityManager;

	public UsuarioRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(Usuario usuario) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(usuario);
		this.entityManager.getTransaction().commit();
	}

	public Usuario buscarUsuarioPorEmaileSenha(String email, String senha) {
		String jpql = "SELECT u FROM Usuario u WHERE u.email = :email and u.senha = :senha";
		try {
			Usuario user = this.entityManager.createQuery(jpql, Usuario.class)
					.setParameter("email", email)
					.setParameter("senha", senha)
					.getSingleResult();
			return user;
		} catch (NoResultException e) {
			Usuario user = new Usuario();
			return user;
		}
	}

	public Usuario buscaUsuarioPorId(Long id) {
		String jpql = "SELECT u FROM Usuario u WHERE u.id = :id";
		try {
			Usuario user = this.entityManager.createQuery(jpql, Usuario.class)
					.setParameter("id", id)
					.getSingleResult();
			return user;
		} catch (NoResultException e) {
			return new Usuario();
		}
	}

	public List<Usuario> buscarUsuarios() {
		String jpql = "SELECT u FROM Usuario u";
		return this.entityManager.createQuery(jpql, Usuario.class).getResultList();
	}

	public void atualizar(Usuario usuario) {
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(usuario);
		this.entityManager.getTransaction().commit();
	}

  public void remove(Usuario usuario) {
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(usuario);
		this.entityManager.remove(usuario);
		this.entityManager.getTransaction().commit();
  }
}
