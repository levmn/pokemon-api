package br.com.fiap.connection;

/*
Na raíz do projeto temos o arquivo .env.sample com as variáveis definidas, ele deve ser duplicado e transformado em .env
para que a aplicação reconheça o login no banco de dados:

    DB_USER = usuário
    DB_PASSWORD = senha
*/

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
    private static final Dotenv dotenv = Dotenv.load();
    private java.sql.Connection conn;

    public java.sql.Connection conexao() {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = dotenv.get("DB_URL");
            String user = dotenv.get("DB_USER");
            String password = dotenv.get("DB_PASSWORD");

            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao estabelecer a conexão: " + e.getMessage());
        }
        return null;
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexão com o banco de dados encerrada com sucesso.");
            } catch (SQLException e) {
                System.err.println("Erro ao encerrar a conexão com o banco de dados: " + e.getMessage());
            }
        }
    }
}
