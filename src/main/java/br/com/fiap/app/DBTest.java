package br.com.fiap.app;

/*
 * Testando conexão com o banco de dados
 * */

import br.com.fiap.connection.DBConn;
import br.com.fiap.model.dao.PokemonDAO;
import br.com.fiap.model.vo.Pokemon;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBTest {
    public static void main(String[] args) {
        DBConn conexao = new DBConn();
        Connection conn = null;
        PokemonDAO pokemonDAO = new PokemonDAO();

        try {
            conn = conexao.conexao();
            if (conn != null) {
                System.out.println("Conexão estabelecida com sucesso!");

                List<Pokemon> pokemons = pokemonDAO.getListPokemon();
                if (pokemons.isEmpty()) {
                    System.out.println("Nenhum pokemon encontrado.");
                } else {
                    System.out.println("Pokémons salvos:");
                    for (Pokemon pokemon : pokemons) {
                        System.out.println(pokemon.toString());
                    }
                }
            } else {
                System.out.println("Falha ao estabelecer a conexão.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        } finally {
            if (conn != null) {
                System.out.println("Encerrando conexão...");
                try {
                    conn.close();
                    System.out.println("Conexão encerrada com sucesso.");
                } catch (SQLException e) {
                    System.err.println("Erro ao encerrar a conexão: " + e.getMessage());
                }
            }
        }
    }
}
