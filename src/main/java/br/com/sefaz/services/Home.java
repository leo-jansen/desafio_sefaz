package br.com.sefaz.services;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sefaz.entities.Usuario;
import br.com.sefaz.repositories.UsuarioRepository;
import br.com.sefaz.util.JPAUtil;

public class Home implements Path {
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager entityManager = JPAUtil.getEntityManager();
		UsuarioRepository usuarioRepository = new UsuarioRepository(entityManager);
		List<Usuario> list = usuarioRepository.buscarUsuarios();
		entityManager.close();

		request.setAttribute("usuarios", list);

		return "forward:home.jsp";
	}

}
