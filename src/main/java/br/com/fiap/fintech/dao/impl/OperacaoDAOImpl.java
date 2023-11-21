package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.OperacaoDAO;
import br.com.fiap.fintech.enums.TipoOperacao;
import br.com.fiap.fintech.model.Operacao;
import br.com.fiap.fintech.util.JDBCOracleUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperacaoDAOImpl implements OperacaoDAO {
	
	private static final String SELECT_BY_ID = "SELECT * FROM operacao WHERE id = ?";
	private static final String SELECT_ALL = "SELECT * FROM operacao";
	private static final String INSERT = "INSERT INTO operacao (id, nome, descricao, tipo_operacao, montante, data_hora, conta_id) VALUES (operacao_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_BY_CONTA = "SELECT * FROM operacao WHERE conta_id = ? AND tipo_operacao = ?";
	
	@Override
	public Operacao findById(Integer id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Operacao operacao = null;
		
		try {
			connection = JDBCOracleUtil.getConnection();
			assert connection != null;
			statement = connection.prepareStatement(SELECT_BY_ID);
			statement.setInt(1, id);
			result = statement.executeQuery();
			
			if (result.next()) {
				operacao = new Operacao();
				operacao.setId(result.getInt("id"));
				operacao.setNome(result.getString("nome"));
				operacao.setDescricao(result.getString("descricao"));
				operacao.setTipoOperacao(TipoOperacao.valueOf(result.getString("tipo_operacao")));
				operacao.setMontante(result.getDouble("montante"));
				operacao.setDataHora(result.getDate("data_hora"));
				operacao.setContaId(result.getInt("conta_id"));
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
		return operacao;
	}
	
	@Override
	public List<Operacao> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Operacao> operacoes = new ArrayList<>();
		
		try {
			connection = JDBCOracleUtil.getConnection();
			assert connection != null;
			statement = connection.prepareStatement(SELECT_ALL);
			result = statement.executeQuery();
			
			while (result.next()) {
				Operacao operacao = new Operacao();
				operacao.setId(result.getInt("id"));
				operacao.setNome(result.getString("nome"));
				operacao.setDescricao(result.getString("descricao"));
				operacao.setMontante(result.getDouble("montante"));
				operacao.setDataHora(result.getDate("data_hora"));
				operacao.setTipoOperacao(TipoOperacao.valueOf(result.getString("tipo_operacao")));
				operacao.setContaId(result.getInt("conta_id"));
				operacoes.add(operacao);
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
		return operacoes;
	}
	
	@Override
	public Operacao save(Operacao operacao) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = JDBCOracleUtil.getConnection();
			assert connection != null;
			statement = connection.prepareStatement(INSERT, new String[] {"id"});
			
			statement.setString(1, operacao.getNome());
			statement.setString(2, operacao.getDescricao());
			statement.setString(3, String.valueOf(operacao.getTipoOperacao()));
			statement.setDouble(4, operacao.getMontante());
			statement.setDate(5, new Date(operacao.getDataHora().getTime()));
			statement.setInt(6, operacao.getContaId());
			
			int rowsAffected = statement.executeUpdate();
			
			if (rowsAffected == 1) {
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					int generatedId = resultSet.getInt(1);
					
					Operacao operacaoSalva = new Operacao();
					operacaoSalva.setId(generatedId);
					operacaoSalva.setNome(operacao.getNome());
					operacaoSalva.setDescricao(operacao.getDescricao());
					operacaoSalva.setTipoOperacao(operacao.getTipoOperacao());
					operacaoSalva.setMontante(operacao.getMontante());
					operacaoSalva.setDataHora(operacao.getDataHora());
					operacaoSalva.setContaId(operacao.getContaId());
					
					System.out.println("Operação Salva com sucesso, ID: " + generatedId);
					return operacaoSalva;
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

	public List<Operacao> findByConta(Integer id, TipoOperacao tipoOperacao) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Operacao> operacoes = new ArrayList<>();

		try {
			connection = JDBCOracleUtil.getConnection();
			assert connection != null;
			statement = connection.prepareStatement(SELECT_BY_CONTA);
			statement.setInt(1, id);
			statement.setString(2, tipoOperacao.toString());
			result = statement.executeQuery();

			while (result.next()) {
				Operacao operacao = new Operacao();
				operacao.setId(result.getInt("id"));
				operacao.setNome(result.getString("nome"));
				operacao.setDescricao(result.getString("descricao"));
				operacao.setMontante(result.getDouble("montante"));
				operacao.setDataHora(result.getDate("data_hora"));
				operacao.setTipoOperacao(TipoOperacao.valueOf(result.getString("tipo_operacao")));
				operacao.setContaId(result.getInt("conta_id"));
				operacoes.add(operacao);
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
		return operacoes;
	}
	
}
