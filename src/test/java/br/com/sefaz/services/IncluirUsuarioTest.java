package br.com.sefaz.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

@TestInstance(Lifecycle.PER_CLASS)
public class IncluirUsuarioTest {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private static boolean conected;

	@BeforeAll
	public void setUp() {
		this.request = mock(HttpServletRequest.class);
		this.response = mock(HttpServletResponse.class);
		this.session = mock(HttpSession.class);
	}

	@Test
	public void testExecutaLogado() throws ServletException, IOException {
		// Montando cenario
		Mockito.doAnswer(new Answer<Void>() {
			@Override
    	public Void answer(InvocationOnMock invocation) throws Throwable {
				IncluirUsuarioTest.conected = true;
				return null;
			}
		}).when(request).setAttribute("conected", true);

		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("isConnected")).thenReturn(true);

		IncluirUsuario incluirUsuario = new IncluirUsuario();

		// Execução
		String retorno = incluirUsuario.executa(request, response);

		// Validação
		assertEquals(true, conected);
		assertEquals("forward:usuarioForm.jsp", retorno);
	}

	@Test
	public void testExecutaDeslogado() throws ServletException, IOException {
		// Montando cenario
		Mockito.doAnswer(new Answer<Void>() {
			@Override
    	public Void answer(InvocationOnMock invocation) throws Throwable {
				IncluirUsuarioTest.conected = false;
				return null;
			}
		}).when(request).setAttribute("conected", false);


		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("isConnected")).thenReturn(null);

		IncluirUsuario incluirUsuario = new IncluirUsuario();

		// Execução
		String retorno = incluirUsuario.executa(request, response);

		// Validação
		assertEquals(false, conected);
		assertEquals("forward:usuarioForm.jsp", retorno);
	}
}
