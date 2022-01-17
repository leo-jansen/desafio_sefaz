package br.com.sefaz.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class LogoutTest {
	@Test
	public void testExecuta() throws ServletException, IOException {
		// Montando cenario
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		Logout logout = new Logout();

		// Execução
		String retorno = logout.executa(request, response);

		// Validação
		assertEquals("forward:login.jsp", retorno);
	}
}
