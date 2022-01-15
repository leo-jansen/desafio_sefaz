package br.com.sefaz.services;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sefaz.entities.Telefone;
import br.com.sefaz.entities.Usuario;
import br.com.sefaz.repositories.UsuarioRepository;
import br.com.sefaz.util.JPAUtil;

public class Alterar implements Path{

	@Override
	public String exeuta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		int ddd = Integer.valueOf(request.getParameter("ddd"));
		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");

		EntityManager entityManager = JPAUtil.getEntityManager();
		UsuarioRepository usuarioRepository = new UsuarioRepository(entityManager);
		Usuario usuario = usuarioRepository.buscaUsuarioPorId(id);
		
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		Telefone telefone = new Telefone(ddd, numero, tipo);
		usuario.setTelefone(telefone);

		usuarioRepository.atualizar(usuario);

		entityManager.close();

		return "redirect:/sefaz/home";
	}
	
}
