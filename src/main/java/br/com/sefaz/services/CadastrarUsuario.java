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

public class CadastrarUsuario implements Path {
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		int ddd = Integer.valueOf(request.getParameter("ddd"));
		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");

		EntityManager entityManager = JPAUtil.getEntityManager();
		UsuarioRepository usuarioRepository = new UsuarioRepository(entityManager);
		List<Usuario> usuarios = usuarioRepository.buscarUsuarioPorEmail(email);

		if (usuarios.size() >= 1) {
			request.setAttribute("emailUtilizado", "ok");
			return "forward:usuarioForm.jsp";
		} else {
			Usuario usuario = new Usuario(nome, email, senha, ddd, numero, tipo);
			usuarioRepository.cadastrar(usuario);
			entityManager.close();
			return "redirect:/sefaz/home";
		}
	}

}
