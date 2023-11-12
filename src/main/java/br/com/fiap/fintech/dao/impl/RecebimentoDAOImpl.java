package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.RecebimentoDAO;
import br.com.fiap.fintech.enums.Categoria;
import br.com.fiap.fintech.model.Recebimento;
import br.com.fiap.fintech.util.JDBCOracleUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecebimentoDAOImpl implements RecebimentoDAO {
	
	private static final String SELECT_BY_ID = "SELECT * FROM recebimento WHERE id = ?";
	private static final String SELECT_ALL = "SELECT op.nome, op.descricao, op.montante, recebimento.id, recebimento" +
			".categoria FROM recebimento INNER JOIN operacao op ON recebimento.id = op.id";
	private static final String INSERT = "INSERT INTO recebimento (id, categoria) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE recebimento SET categoria = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM recebimento WHERE id = ?";
	
	@Override
	public Recebimento findById(Integer id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Recebimento recebimento = null;
		
		try {
			connection = JDBCOracleUtil.getConnection();
			assert connection != null;
			statement = connection.prepareStatement(SELECT_BY_ID);
			statement.setInt(1, id);
			result = statement.executeQuery();
			
			if (result.next()) {
				recebimento = new Recebimento();
				recebimento.setNome(result.getString("nome"));
				recebimento.setDescricao(result.getString("descricao"));
				recebimento.setMontante(result.getDouble("montante"));
				recebimento.setId(result.getInt("id"));
				recebimento.setCategoria(Categoria.valueOf(result.getString("categoria")));
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
		return recebimento;
	}
	
	@Override
	public List<Recebimento> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Recebimento> recebimentos = new ArrayList<>();
		
		try {
			connection = JDBCOracleUtil.getConnection();
			assert connection != null;
			statement = connection.prepareStatement(SELECT_ALL);
			result = statement.executeQuery();
			
			while (result.next()) {
				Recebimento recebimento = new Recebimento();
				recebimento.setNome(result.getString("nome"));
				recebimento.setDescricao(result.getString("descricao"));
				recebimento.setMontante(result.getDouble("montante"));
				recebimento.setId(result.getInt("id"));
				recebimento.setCategoria(Categoria.valueOf(result.getString("categoria")));
				recebimentos.add(recebimento);
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
		return recebimentos;
	}
	
	@Override
	public Recebimento save(Recebimento recebimento) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = JDBCOracleUtil.getConnection();
			assert connection != null;
			statement = connection.prepareStatement(INSERT);
			
			statement.setInt(1, recebimento.getId());
			statement.setString(2, recebimento.getCategoria().toString());
			
			int rowsAffected = statement.executeUpdate();
			
			if (rowsAffected == 1) {
				resultSet = statement.getGeneratedKeys();
				Recebimento recebimentoSalvo = new Recebimento();
				recebimentoSalvo.setId(recebimento.getId());
				recebimentoSalvo.setCategoria(recebimento.getCategoria());
				
				System.out.println("Recebimento Salvo com sucesso");
				return recebimentoSalvo;
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
