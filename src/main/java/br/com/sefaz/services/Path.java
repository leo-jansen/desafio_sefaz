package br.com.sefaz.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Path {
	String exeuta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
