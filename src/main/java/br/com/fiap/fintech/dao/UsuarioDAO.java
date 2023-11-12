package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.model.Usuario;
import java.util.List;

public interface UsuarioDAO {
	
	Usuario findById(Integer id);
	List<Usuario> findAll();
	Usuario save(Usuario usuario);
}
