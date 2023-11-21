package br.com.fiap.fintech.service;

import br.com.fiap.fintech.dao.impl.ContaDAOImpl;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.model.Despesa;
import br.com.fiap.fintech.model.Operacao;
import br.com.fiap.fintech.model.Usuario;
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

	public Conta atualizaSaldo(Usuario usuario, Operacao operacao) {
		Conta conta = contaDAO.findById(usuario.getContaId());
		if (conta != null && operacao instanceof Despesa) {
			Double saldoAnterior = conta.getSaldo();
			Double saldoAtualizado = saldoAnterior -= operacao.getMontante();
			conta.setSaldo(saldoAtualizado);
			return contaDAO.update(conta);
		} else if (conta != null) {
			Double saldoAnterior = conta.getSaldo();
			Double saldoAtualizado = saldoAnterior += operacao.getMontante();
			conta.setSaldo(saldoAtualizado);
			return contaDAO.update(conta);
		}
		return null;
	}

	public Conta getContaByUser(Integer id) {
		return contaDAO.findById(id);
	}
	
}
