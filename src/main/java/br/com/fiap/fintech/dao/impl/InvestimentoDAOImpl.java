package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.InvestimentoDAO;
import br.com.fiap.fintech.enums.Categoria;
import br.com.fiap.fintech.model.Investimento;
import br.com.fiap.fintech.util.JDBCOracleUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvestimentoDAOImpl implements InvestimentoDAO {
	
	private static final String SELECT_BY_ID = "SELECT * FROM investimento WHERE id = ?";
	private static final String SELECT_ALL = "SELECT op.nome, op.descricao, op.montante, investimento.id, " +
			"investimento.categoria FROM investimento INNER JOIN operacao op ON investimento.id = op.id";
	private static final String INSERT = "INSERT INTO investimento (id, categoria) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE investimento SET categoria = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM investimento WHERE id = ?";
	
	@Override
	public Investimento findById(Integer id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Investimento investimento = null;
		
		try {
			connection = JDBCOracleUtil.getConnection();
			assert connection != null;
			statement = connection.prepareStatement(SELECT_BY_ID);
			statement.setInt(1, id);
			result = statement.executeQuery();
			
			if (result.next()) {
				investimento = new Investimento();
				investimento.setNome(result.getString("nome"));
				investimento.setDescricao(result.getString("descricao"));
				investimento.setMontante(result.getDouble("montante"));
				investimento.setId(result.getInt("id"));
				investimento.setCategoria(Categoria.valueOf(result.getString("categoria")));
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
		return investimento;
	}
	
	@Override
	public List<Investimento> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Investimento> investimentos = new ArrayList<>();
		
		try {
			connection = JDBCOracleUtil.getConnection();
			assert connection != null;
			statement = connection.prepareStatement(SELECT_ALL);
			result = statement.executeQuery();
			
			while (result.next()) {
				Investimento investimento = new Investimento();
				investimento.setNome(result.getString("nome"));
				investimento.setDescricao(result.getString("descricao"));
				investimento.setMontante(result.getDouble("montante"));
				investimento.setId(result.getInt("id"));
				investimento.setCategoria(Categoria.valueOf(result.getString("categoria")));
				investimentos.add(investimento);
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
		return investimentos;
	}
	
	@Override
	public Investimento save(Investimento investimento) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = JDBCOracleUtil.getConnection();
			assert connection != null;
			statement = connection.prepareStatement(INSERT);
			
			statement.setInt(1, investimento.getId());
			statement.setString(2, investimento.getCategoria().toString());
			
			int rowsAffected = statement.executeUpdate();
			
			if (rowsAffected == 1) {
				Investimento investimentoSalvo = new Investimento();
				investimentoSalvo.setId(investimento.getId());
				investimentoSalvo.setCategoria(investimento.getCategoria());
				
				System.out.println("Investimento salvo com sucesso");
				return investimentoSalvo;
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
