package br.com.fiap.fintech.servlets;

import br.com.fiap.fintech.service.OperacaoService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "OperacaoServlet",
        urlPatterns={"/OperacaoServlet/recebimento",
                "/OperacaoServlet/despesa",
                "/OperacaoServlet/investimento"
        })
public class OperacaoServlet extends HttpServlet {

    OperacaoService operacaoService = new OperacaoService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();
        if (path.equals("/OperacaoServlet/recebimento")) {
            operacaoService.salvarRecebimento(request);
            response.sendRedirect("../home.jsp");
        } else if (path.equals("/OperacaoServlet/despesa")) {
            operacaoService.salvarDespesa(request);
            response.sendRedirect("../home.jsp");
        } else if (path.equals("/OperacaoServlet/investimento")) {
            operacaoService.salvarInvestimento(request);
            response.sendRedirect("../home.jsp");
        } else {
            System.out.println("Houve alguma falha no Servlet");
        }
    }
}
