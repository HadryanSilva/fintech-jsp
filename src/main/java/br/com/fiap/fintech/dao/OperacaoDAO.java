package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.model.Operacao;
import java.util.List;

public interface OperacaoDAO {
	
	Operacao findById(Integer id);
	List<Operacao> findAll();
	Operacao save(Operacao operacao);
	
}
