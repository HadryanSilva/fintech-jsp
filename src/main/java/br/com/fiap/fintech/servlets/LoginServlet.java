package br.com.fiap.fintech.servlets;

import br.com.fiap.fintech.dao.impl.UsuarioDAOImpl;
import br.com.fiap.fintech.enums.TipoOperacao;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.service.ContaService;
import br.com.fiap.fintech.service.OperacaoService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value="/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private final UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
	private final ContaService contaService = new ContaService();
	private final OperacaoService operacaoService = new OperacaoService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String email = request.getParameter("email");
		String senha = request.getParameter("password");
		
		Usuario usuario = usuarioDAO.findByLogin(email, senha);
		
		if (usuario != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", usuario);
			Conta conta = contaService.getContaByUser(usuario.getContaId());
			request.setAttribute("saldo", conta.getSaldo());
			request.setAttribute("totalRecebimentos", operacaoService.getTotalPorTipoOperacao(conta.getId(), TipoOperacao.RECEBIMENTO));
			request.setAttribute("totalDespesas", operacaoService.getTotalPorTipoOperacao(conta.getId(), TipoOperacao.DESPESA));
			request.setAttribute("totalInvestimentos", operacaoService.getTotalPorTipoOperacao(conta.getId(), TipoOperacao.INVESTIMENTO));
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
		} else {
			PrintWriter writer = response.getWriter();
			writer.println("<html><body>");
			writer.println("<h1>" + "Usuario Inexistente" + "</h1>");
			writer.println("</body></html>");
		}
	}

}
