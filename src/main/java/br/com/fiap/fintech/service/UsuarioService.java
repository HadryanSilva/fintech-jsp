package br.com.fiap.fintech.service;

import br.com.fiap.fintech.dao.impl.UsuarioDAOImpl;
import br.com.fiap.fintech.enums.RoleUsuario;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;

public class UsuarioService {
	
	private final UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
	
	public Usuario salvar(HttpServletRequest request, HttpServletResponse response, Conta conta) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Usuario usuario = new Usuario();
		usuario.setName(name);
		usuario.setEmail(email);
		usuario.setPassword(password);
		usuario.setRole(RoleUsuario.USER);
		usuario.setDataCriacao(new Date());
		usuario.setContaId(conta.getId());
		
		return usuarioDAO.save(usuario);
	}
	
	public Usuario findByEmail(String email) {
		return usuarioDAO.findByLogin(email, null);
	}
	
}
