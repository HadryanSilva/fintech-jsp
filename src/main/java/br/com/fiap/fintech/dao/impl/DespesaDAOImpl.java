package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.DespesaDAO;
import br.com.fiap.fintech.enums.Categoria;
import br.com.fiap.fintech.model.Despesa;
import br.com.fiap.fintech.util.JDBCOracleUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DespesaDAOImpl implements DespesaDAO {
	
	private static final String SELECT_BY_ID = "SELECT * FROM despesa WHERE id = ?";
	private static final String SELECT_ALL = "SELECT op.nome, op.descricao, op.montante, despesa.id, despesa" +
			".categoria FROM despesa INNER JOIN operacao op ON op.id = despesa.id";
	private static final String INSERT = "INSERT INTO despesa (id, categoria) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE despesa SET categoria = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM despesa WHERE id = ?";
	
	@Override
	public Despesa findById(Integer id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Despesa despesa = null;
		
		try {
			connection = JDBCOracleUtil.getConnection();
			assert connection != null;
			statement = connection.prepareStatement(SELECT_BY_ID);
			statement.setInt(1, id);
			result = statement.executeQuery();
			
			if (result.next()) {
				despesa = new Despesa();
				despesa.setNome(result.getString("nome"));
				despesa.setDescricao(result.getString("descricao"));
				despesa.setMontante(result.getDouble("montante"));
				despesa.setId(result.getInt("id"));
				despesa.setCategoria(Categoria.valueOf(result.getString("nome")));
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
		return despesa;
	}
	
	@Override
	public List<Despesa> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Despesa> despesas = new ArrayList<>();
		
		try {
			connection = JDBCOracleUtil.getConnection();
			assert connection != null;
			statement = connection.prepareStatement(SELECT_ALL);
			result = statement.executeQuery();
			
			while (result.next()) {
				Despesa despesa = new Despesa();
				despesa.setNome(result.getString("nome"));
				despesa.setDescricao(result.getString("descricao"));
				despesa.setMontante(result.getDouble("montante"));
				despesa.setId(result.getInt("id"));
				despesa.setCategoria(Categoria.valueOf(result.getString("categoria")));
				despesas.add(despesa);
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
		return despesas;
	}
	
	@Override
	public Despesa save(Despesa despesa) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = JDBCOracleUtil.getConnection();
			assert connection != null;
			statement = connection.prepareStatement(INSERT);
			
			statement.setInt(1, despesa.getId());
			statement.setString(2, despesa.getCategoria().toString());
			
			int rowsAffected = statement.executeUpdate();
			if (rowsAffected == 1) {
				resultSet = statement.getGeneratedKeys();
				Despesa despesaSalva = new Despesa();
				despesaSalva.setId(despesa.getId());
				despesaSalva.setCategoria(despesa.getCategoria());
				
				System.out.println("Despesa salva com sucesso");
				return despesaSalva;
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
