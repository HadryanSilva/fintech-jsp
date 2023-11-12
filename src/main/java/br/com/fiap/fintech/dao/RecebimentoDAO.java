package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.model.Recebimento;
import java.util.List;

public interface RecebimentoDAO {
	
	Recebimento findById(Integer id);
	List<Recebimento> findAll();
	Recebimento save(Recebimento recebimento);
}
