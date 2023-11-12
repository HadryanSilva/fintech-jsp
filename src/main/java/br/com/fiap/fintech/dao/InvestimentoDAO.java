package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.model.Investimento;
import java.util.List;

public interface InvestimentoDAO {
	
	Investimento findById(Integer id);
	List<Investimento> findAll();
	Investimento save(Investimento investimento);
	
}
