package br.com.sefaz.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IncluirUsuario implements Path {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("isConnected") == null) {
			request.setAttribute("conected", false);
		} else {
			request.setAttribute("conected", true);
		}
		return "forward:usuarioForm.jsp";
	}

}
