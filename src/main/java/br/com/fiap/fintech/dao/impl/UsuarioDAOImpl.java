package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.enums.RoleUsuario;
import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.util.JDBCOracleUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {
	
	private static final String SELECT_BY_ID = "SELECT * FROM usuario WHERE id = ?";
	private static final String SELECT_BY_LOGIN = "SELECT * FROM usuario WHERE email = ? AND password = ?";
	private static final String SELECT_BY_EMAIL = "SELECT * FROM usuario WHERE email = ?";
	private static final String SELECT_ALL = "SELECT * FROM usuario";
	private static final String INSERT = "INSERT INTO usuario (id, name, email, password, role, data_criacao, conta_id) VALUES (usuario_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE usuario SET name = ?, email = ?, password = ?, role = ?, data_criacao = ?, conta_id = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM usuario WHERE id = ?";
	
	@Override
	public Usuario findById(Integer id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Usuario usuario = null;
		
		try {
			connection = JDBCOracleUtil.getConnection();
			assert connection != null;
			statement = connection.prepareStatement(SELECT_BY_ID);
			statement.setInt(1, id);
			result = statement.executeQuery();
			
			if (result.next()) {
				usuario = new Usuario();
				usuario.setId(result.getInt("id"));
				usuario.setName(result.getString("nome"));
				usuario.setEmail(result.getString("email"));
				usuario.setPassword(result.getString("password"));
				usuario.setRole(RoleUsuario.valueOf(result.getString("role")));
				usuario.setDataCriacao(result.getDate("data_criacao"));
				usuario.setContaId(result.getInt("conta_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return usuario;
	}
	
	public Usuario findByLogin(String email, String senha) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Usuario usuario = null;
		
		try {
			connection = JDBCOracleUtil.getConnection();
			assert connection != null;
			if (senha != null) {
				statement = connection.prepareStatement(SELECT_BY_LOGIN);
				statement.setString(1, email);
				statement.setString(2, senha);
			} else {
				statement = connection.prepareStatement(SELECT_BY_EMAIL);
				statement.setString(1, email);
			}
			result = statement.executeQuery();
			
			if (result.next()) {
				usuario = new Usuario();
				usuario.setId(result.getInt("id"));
				usuario.setName(result.getString("name"));
				usuario.setEmail(result.getString("email"));
				usuario.setPassword(result.getString("password"));
				usuario.setRole(RoleUsuario.valueOf(result.getString("role")));
				usuario.setDataCriacao(result.getDate("data_criacao"));
				usuario.setContaId(result.getInt("conta_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return usuario;
	}
	
	@Override
	public List<Usuario> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Usuario> usuarios = new ArrayList<>();
		
		try {
			connection = JDBCOracleUtil.getConnection();
			assert connection != null;
			statement = connection.prepareStatement(SELECT_ALL);
			result = statement.executeQuery();
			
			while (result.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(result.getInt("id"));
				usuario.setName(result.getString("name"));
				usuario.setEmail(result.getString("email"));
				usuario.setPassword(result.getString("password"));
				usuario.setRole(RoleUsuario.valueOf(result.getString("role")));
				usuario.setDataCriacao(result.getDate("data_criacao"));
				usuario.setContaId(result.getInt("conta_id"));
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return usuarios;
	}
	
	@Override
	public Usuario save(Usuario usuario) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = JDBCOracleUtil.getConnection();
			statement = connection.prepareStatement(INSERT, new String[]{"id"});
			
			statement.setString(1, usuario.getName());
			statement.setString(2, usuario.getEmail());
			statement.setString(3, usuario.getPassword());
			statement.setString(4, usuario.getRole().toString());
			statement.setDate(5, new Date(usuario.getDataCriacao().getTime()));
			statement.setInt(6, usuario.getContaId());
			
			int rowsAffected = statement.executeUpdate();
			if (rowsAffected == 1) {
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					int generatedId = resultSet.getInt(1);
					
					Usuario usuarioSalvo = new Usuario();
					usuarioSalvo.setId(generatedId);
					usuarioSalvo.setName(usuario.getName());
					usuarioSalvo.setEmail(usuario.getEmail());
					usuarioSalvo.setRole(usuario.getRole());
					usuarioSalvo.setDataCriacao(new Date(usuario.getDataCriacao().getTime()));
					usuarioSalvo.setContaId(usuario.getContaId());
					
					System.out.println("Usuário salvo com sucesso, ID: " + generatedId);
					return usuarioSalvo;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
