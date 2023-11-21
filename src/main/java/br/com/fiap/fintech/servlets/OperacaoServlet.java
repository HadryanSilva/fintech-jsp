package br.com.fiap.fintech.servlets;

import br.com.fiap.fintech.enums.TipoOperacao;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.service.OperacaoService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        incializaTotais(request);
        if (path.equals("/OperacaoServlet/recebimento")) {
            Conta conta = operacaoService.salvarRecebimento(request);
            request.setAttribute("saldo", conta.getSaldo());
            request.setAttribute("totalRecebimentos", operacaoService.getTotalPorTipoOperacao(conta.getId(), TipoOperacao.RECEBIMENTO));

            RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
            dispatcher.forward(request, response);
        } else if (path.equals("/OperacaoServlet/despesa")) {
            Conta conta = operacaoService.salvarDespesa(request);
            request.setAttribute("saldo", conta.getSaldo());
            request.setAttribute("totalDespesas", operacaoService.getTotalPorTipoOperacao(conta.getId(), TipoOperacao.DESPESA));
            RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
            dispatcher.forward(request, response);
        } else if (path.equals("/OperacaoServlet/investimento")) {
            Conta conta = operacaoService.salvarInvestimento(request);
            request.setAttribute("saldo", conta.getSaldo());
            request.setAttribute("totalInvestimentos", operacaoService.getTotalPorTipoOperacao(conta.getId(), TipoOperacao.INVESTIMENTO));
            RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
            dispatcher.forward(request, response);
        } else {
            System.out.println("Houve alguma falha no Servlet");
        }
    }

    private void incializaTotais(HttpServletRequest request) {
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        request.setAttribute("totalInvestimentos", operacaoService.getTotalPorTipoOperacao(user.getContaId(), TipoOperacao.INVESTIMENTO));
        request.setAttribute("totalDespesas", operacaoService.getTotalPorTipoOperacao(user.getContaId(), TipoOperacao.DESPESA));
        request.setAttribute("totalRecebimentos", operacaoService.getTotalPorTipoOperacao(user.getContaId(), TipoOperacao.RECEBIMENTO));
    }
}
