package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.model.Conta;
import java.util.List;

public interface ContaDAO {
	
	Conta findById(int id);
	List<Conta> findAll();
	Conta save(Conta conta);

}
