package br.com.fiap.fintech.service;

import br.com.fiap.fintech.dao.impl.ContaDAOImpl;
import br.com.fiap.fintech.model.Conta;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

public class ContaService {
	
	private final ContaDAOImpl contaDAO = new ContaDAOImpl();
	
	public Conta salvar(HttpServletRequest request, HttpServletResponse response) {
		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");
		
		Conta conta = new Conta();
		conta.setNome(nome);
		conta.setDescricao(descricao);
		conta.setDataCriacao(new Date());
		conta.setSaldo(0.00);
		
		return contaDAO.save(conta);
	}
	
	public List<Conta> listar() {
		return contaDAO.findAll();
	}
	
}
