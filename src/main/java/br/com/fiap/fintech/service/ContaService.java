package br.com.fiap.fintech.service;

import br.com.fiap.fintech.dao.impl.ContaDAOImpl;
import br.com.fiap.fintech.model.Conta;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;

public class ContaService {
	
	private final ContaDAOImpl contaDAO = new ContaDAOImpl();
	
	public void salvarConta(HttpServletRequest request, HttpServletResponse response) {
		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");
		String saldo = request.getParameter("saldo");
		
		Conta conta = new Conta();
		conta.setNome(nome);
		conta.setDescricao(descricao);
		conta.setDataCriacao(new Date());
		conta.setSaldo(Double.valueOf(saldo));
		
		Conta contaSalva = contaDAO.save(conta);
		
		if(contaSalva != null) {
			response.setStatus(201);
		} else {
			response.setStatus(422);
		}
	}
	
}
