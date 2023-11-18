package br.com.fiap.fintech.servlets;

import br.com.fiap.fintech.service.OperacaoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "OperacaoServlet",
        value={"/OperacaoServlet/recebimento",
                "/OperacaoServlet/despesa",
                "/OperacaoServlet/investimento"
        })
public class OperacaoServlet extends HttpServlet {

    OperacaoService operacaoService = new OperacaoService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getContextPath();

        switch (path){
            case "/OperacaoServlet/recebimento":
                operacaoService.salvarRecebimento(request);
            case "/OperacaoServlet/despesa":
                operacaoService.salvarDespesa(request);
            case "/OperacaoServlet/investimento":
                operacaoService.salvarInvestimento(request);
            default:
                System.out.println("Falha");
        }


    }
}
