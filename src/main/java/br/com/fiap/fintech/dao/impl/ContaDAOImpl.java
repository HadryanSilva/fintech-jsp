package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.ContaDAO;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.util.JDBCOracleUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaDAOImpl implements ContaDAO {
	
	private static final String SELECT_BY_ID = "SELECT * FROM conta WHERE id = ?";
	private static final String SELECT_ALL = "SELECT * FROM conta";
	private static final String INSERT = "INSERT INTO conta (id, nome, descricao, data_criacao, saldo) VALUES (conta_seq.NEXTVAL, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE conta SET nome = ?, descricao = ?, data_criacao = ?, saldo = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM conta WHERE id = ?";
	
	@Override
	public Conta findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Conta conta = null;
		
		try {
			connection = JDBCOracleUtil.getConnection();
			assert connection != null;
			statement = connection.prepareStatement(SELECT_BY_ID);
			statement.setInt(1, id); // Defina o valor do parâmetro da consulta
			result = statement.executeQuery();
			
			if (result.next()) {
				conta = new Conta();
				conta.setId(result.getInt("id"));
				conta.setNome(result.getString("nome"));
				conta.setDescricao(result.getString("descricao"));
				conta.setDataCriacao(result.getDate("data_criacao"));
				conta.setSaldo(result.getDouble("saldo"));
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
		return conta;
	}
	@Override
	public List<Conta> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Conta> contas = new ArrayList<>();
		
		try {
			connection = JDBCOracleUtil.getConnection();
			assert connection != null;
			statement = connection.prepareStatement(SELECT_ALL);
			result = statement.executeQuery();
			
			while (result.next()) {
				Conta conta = new Conta();
				conta.setId(result.getInt("id"));
				conta.setNome(result.getString("nome"));
				conta.setDescricao(result.getString("descricao"));
				conta.setDataCriacao(result.getDate("data_criacao"));
				conta.setSaldo(result.getDouble("saldo"));
				contas.add(conta);
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
		
		return contas;
	}
	
	@Override
	public Conta save(Conta conta) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			connection = JDBCOracleUtil.getConnection();
			statement = connection.prepareStatement(INSERT, new String[]{"id"});
			
			statement.setString(1, conta.getNome());
			statement.setString(2, conta.getDescricao());
			statement.setDate(3, new Date(conta.getDataCriacao().getTime()));
			statement.setDouble(4, conta.getSaldo());
			
			int rowsAffected = statement.executeUpdate();
			if (rowsAffected == 1) {
				result = statement.getGeneratedKeys();
				if (result.next()) {
					int generatedId = result.getInt(1);
					
					Conta contaSalva = new Conta();
					contaSalva.setId(generatedId);
					contaSalva.setNome(conta.getNome());
					contaSalva.setDescricao(conta.getDescricao());
					contaSalva.setDataCriacao(conta.getDataCriacao());
					contaSalva.setSaldo(conta.getSaldo());
					
					System.out.println("Conta salva com sucesso, ID: " + generatedId);
					return contaSalva;
				}
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
		return conta;
	}
}
