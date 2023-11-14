package br.com.fiap.fintech.servlets;

import br.com.fiap.fintech.dao.impl.UsuarioDAOImpl;
import br.com.fiap.fintech.model.Usuario;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value="/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("password");
		
		Usuario usuario = usuarioDAO.findByLogin(email, senha);
		
		if (usuario != null) {
			response.sendRedirect("home.jsp");
		} else {
			PrintWriter writer = response.getWriter();
			writer.println("<html><body>");
			writer.println("<h1>" + "Usuario Inexistente" + "</h1>");
			writer.println("</body></html>");
		}
	}

}
