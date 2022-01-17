package br.com.sefaz.services;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sefaz.entities.Usuario;
import br.com.sefaz.repositories.UsuarioRepository;
import br.com.sefaz.util.JPAUtil;

public class Validacao implements Path {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		EntityManager entityManager = JPAUtil.getEntityManager();
		UsuarioRepository usuarioRepository = new UsuarioRepository(entityManager);
		Usuario usuario = usuarioRepository.buscarUsuarioPorEmaileSenha(email, senha);
		entityManager.close();
		if (email.equals(usuario.getEmail()) && senha.equals(usuario.getSenha())) {
			HttpSession session = request.getSession();
			session.setAttribute("isConnected", true);
			return "redirect:/sefaz/home";
		} else {
			request.setAttribute("emailSenha", "ok");
			return "forward:login.jsp";
		}
	}
}
