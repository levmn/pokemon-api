package br.com.fiap.model.dao;

import br.com.fiap.connection.DBConn;
import br.com.fiap.model.vo.Pokemon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokemonDAO {

    private Connection conn;

    public PokemonDAO() {
        this.conn = new DBConn().conexao();
    }

    public String insertPokemon(Pokemon pokemon) throws SQLException {
        String sql = "INSERT INTO tb_pokemon (id, name, types, abilities) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, pokemon.getId());
        stmt.setString(2, pokemon.getName());
        stmt.setString(3, pokemon.getTypes());
        stmt.setString(4, pokemon.getAbilities());
        stmt.execute();

        return "Pokémon cadastrado com sucesso!";
    }

    public String removePokemon(int id) throws SQLException {
        String sql = "DELETE FROM tb_pokemon WHERE id = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();

        return "Pokémon removido com sucesso!";
    }

    public Pokemon getPokemonById(int id) throws SQLException {
        String sql = "SELECT * FROM tb_pokemon WHERE id = ?";
        Pokemon pokemon = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pokemon = new Pokemon();
                pokemon.setId(rs.getInt(1));
                pokemon.setName(rs.getString(2));
                pokemon.setTypes(rs.getString(3));
                pokemon.setAbilities(rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao buscar Pokémon pelo id: " + id, e);
        }

        return pokemon;
    }

    public List<Pokemon> getListPokemon() throws SQLException {
        List<Pokemon> listPokemon = new ArrayList<Pokemon>();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tb_pokemon");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Pokemon pokemon = new Pokemon();
            pokemon.setId(rs.getInt("id"));
            pokemon.setName(rs.getString("name"));
            pokemon.setTypes(rs.getString("types"));
            pokemon.setAbilities(rs.getString("abilities"));

            listPokemon.add(pokemon);
        }
        return listPokemon;
    }
}
