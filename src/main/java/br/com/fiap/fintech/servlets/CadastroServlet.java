package br.com.fiap.fintech.servlets;

import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.service.ContaService;
import br.com.fiap.fintech.service.UsuarioService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CadastroServlet", value="/CadastroServlet")
public class CadastroServlet extends HttpServlet {
	private final UsuarioService usuarioService = new UsuarioService();
	private final ContaService contaService = new ContaService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		if (usuarioService.findByEmail(email) == null) {
			Conta contaSalva = contaService.salvar(request, response);
			Usuario usuarioSalvo = usuarioService.salvar(request, response, contaSalva);
			response.sendRedirect("index.html");
		} else {
			PrintWriter writer = response.getWriter();
			writer.println("<html><body>");
			writer.println("<h1>Ja existe um usuario cadastrado com este email</h1>");
			writer.println("</body></html>");
			writer.close();
		}
	}
	
}
