package br.com.fiap.fintech.servlets;

import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.service.ContaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ContaServlet", value="/ContaServlet")
public class ContaServlet extends HttpServlet {
	
	ContaService contaService = new ContaService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		contaService.salvar(request, response);
		response.sendRedirect("operacao-concluida.html");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String action = request.getContextPath();
		if (action.equals("/fintech")) {
			List<Conta> contas = contaService.listar();
			request.setAttribute("contas", contas);
			request.getRequestDispatcher("listar-contas.jsp").forward(request, response);
		}
	}
	
}
