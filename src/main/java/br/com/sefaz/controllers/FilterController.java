package br.com.sefaz.controllers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/*")
public class FilterController implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		HttpSession session = httpServletRequest.getSession();
		String uri = httpServletRequest.getRequestURI();

		if (session.getAttribute("isConnected") == null
				&& !(uri.equals("/sefaz/incluirUsuario") || uri.equals("/sefaz/cadastrarUsuario")
						|| uri.equals("/sefaz/login") || uri.equals("/sefaz/validacao"))) {
			httpServletResponse.sendRedirect("/sefaz/login");
		} else {
			chain.doFilter(httpServletRequest, httpServletResponse);
		}
	}

}
