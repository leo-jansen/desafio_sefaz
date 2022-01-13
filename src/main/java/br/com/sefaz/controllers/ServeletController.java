package br.com.sefaz.controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sefaz.services.Path;

@WebServlet(urlPatterns = "/")
public class ServeletController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] url = req.getRequestURI().split("/");
		String nomeClasse = String.format("br.com.sefaz.services.%s",
				url[2].substring(0, 1).toUpperCase().concat(url[2].substring(1)));
		try {
			Path path = (Path) Class.forName(nomeClasse).getDeclaredConstructor().newInstance();
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
