package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Usando o IP local '127.0.0.1' como host
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/clinica";  // Verifique se o nome do banco está correto
    private static final String USER = "root";  // Usuário do banco
    private static final String PASSWORD = "";  // Senha do banco, caso haja

    public static Connection getConnection() throws SQLException {
        try {
            // Estabelecendo a conexão com o banco de dados
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar com o banco de dados: " + e.getMessage(), e);
        }
    }
}
