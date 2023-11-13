package br.com.fiap.fintech.servlets;

import br.com.fiap.fintech.service.ContaService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ContaServlet", value="/ContaServlet")
public class ContaServlet extends HttpServlet {
	
	ContaService contaService = new ContaService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		contaService.salvarConta(request, response);
		
	}
	
}
