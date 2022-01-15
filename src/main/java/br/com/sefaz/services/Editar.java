package br.com.sefaz.services;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sefaz.entities.Usuario;
import br.com.sefaz.repositories.UsuarioRepository;
import br.com.sefaz.util.JPAUtil;

public class Editar implements Path{

	@Override
	public String exeuta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));

		EntityManager entityManager = JPAUtil.getEntityManager();
		UsuarioRepository usuarioRepository = new UsuarioRepository(entityManager);
		Usuario usuario = usuarioRepository.buscaUsuarioPorId(id);
		entityManager.close();

		request.setAttribute("usuario", usuario);

		return "forward:editarUsuario.jsp";
	}
	
}
