package br.com.sefaz.controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sefaz.services.Path;

@WebServlet(urlPatterns = "/")
public class ServeletController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String uri = req.getRequestURI();
		if (session.getAttribute("isConnected") == null
				&& !(uri.equals("/sefaz/incluirUsuario") || uri.equals("/sefaz/cadastrarUsuario")
						|| uri.equals("/sefaz/login") || uri.equals("/sefaz/validacao"))) {
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/login.jsp");
			rd.forward(req, resp);
		} else {
			String[] nomeClasse = uri.split("/");
			String caminhoClasse = String.format("br.com.sefaz.services.%s",
					nomeClasse[2].substring(0, 1).toUpperCase().concat(nomeClasse[2].substring(1)));
			try {
				Path path = (Path) Class.forName(caminhoClasse).getDeclaredConstructor().newInstance();
				String nome = path.exeuta(req, resp);
				String[] tipoEEndereco = nome.split(":");
				if (tipoEEndereco[0].equals("forward")) {
					RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/" + tipoEEndereco[1]);
					rd.forward(req, resp);
				} else {
					resp.sendRedirect(tipoEEndereco[1]);
				}
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
				throw new ServletException(e);
			}
		}
	}
}
