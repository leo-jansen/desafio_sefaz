package br.com.sefaz.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.sefaz.entities.Usuario;

public class UsuarioRepository {
  private EntityManager entityManager;

	public UsuarioRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(Usuario usuario) {
		this.entityManager.persist(usuario);
	}

	public List<Usuario> buscarUsuarios() {
    String jpql = "SELECT u FROM tb_usuarios u";
    return this.entityManager.createQuery(jpql, Usuario.class).getResultList();
  }
}
