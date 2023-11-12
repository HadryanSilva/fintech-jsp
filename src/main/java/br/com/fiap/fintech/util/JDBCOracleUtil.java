package br.com.fiap.fintech.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCOracleUtil {
	
	public static Connection getConnection() {
		String jdbcUrl = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
		String username = "RM551037";
		String password = "180401";
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			return DriverManager.getConnection(jdbcUrl, username, password);
		} catch (ClassNotFoundException e) {
			System.err.println(
					"Driver JDBC não encontrado. Certifique-se de que o driver Oracle JDBC esteja no classpath.");
		} catch (SQLException e) {
			System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
		}
		return null;
	}
	
}
