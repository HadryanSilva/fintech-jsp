package br.com.fiap.fintech.dao;


import br.com.fiap.fintech.model.Despesa;
import java.util.List;

public interface DespesaDAO {
	
	Despesa findById(Integer id);
	List<Despesa> findAll();
	Despesa save(Despesa despesa);
	
}
