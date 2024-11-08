package br.com.fiap.model.dao;

import br.com.fiap.connection.DBConn;
import br.com.fiap.model.vo.Pokemon;
import br.com.fiap.model.vo.Trainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAO {
    private Connection conn;

    public TrainerDAO() {
        this.conn = new DBConn().conexao();
    }

    public String insertTrainer(Trainer trainer) throws SQLException {
        String sql = "INSERT INTO tb_trainer (id, name) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, trainer.getId());
        stmt.setString(2, trainer.getName());
        stmt.executeUpdate();

        return "Trainer cadastrado com sucesso!";
    }

    public Trainer getTrainerById(int id) throws SQLException {
        String sql = "SELECT * FROM tb_trainer WHERE id = ?";
        Trainer trainer = null;
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            trainer = new Trainer(rs.getInt("id"), rs.getString("name"));
            trainer.setTeam(getTrainerTeam(trainer.getId()));
        }
        return trainer;
    }

    public List<Pokemon> getTrainerTeam(int trainerId) throws SQLException {
        String sql = "SELECT p.* FROM tb_pokemon p JOIN tb_trainer_team tt ON p.id = tt.pokemon_id WHERE tt.trainer_id = ?";
        List<Pokemon> team = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, trainerId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Pokemon pokemon = new Pokemon();
            pokemon.setId(rs.getInt("id"));
            pokemon.setName(rs.getString("name"));
            pokemon.setTypes(rs.getString("types"));
            pokemon.setAbilities(rs.getString("abilities"));
            team.add(pokemon);
        }
        return team;
    }

    public String addPokemonToTrainerTeam(int trainerId, Pokemon pokemon) throws SQLException {
        String sql = "INSERT INTO tb_trainer_team (trainer_id, pokemon_id) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, trainerId);
        stmt.setInt(2, pokemon.getId());
        stmt.executeUpdate();

        return "Pokémon " + pokemon.getName() + " adicionado com sucesso ao time do treinador!";
    }

    public String removePokemonFromTrainerTeam(int trainerId, int pokemonId) throws SQLException {
        String sql = "DELETE FROM tb_trainer_team WHERE trainer_id = ? AND pokemon_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, trainerId);
        stmt.setInt(2, pokemonId);
        int rowsAffected = stmt.executeUpdate();

        return rowsAffected > 0 ? "Pokémon removido do time com sucesso!" : "Pokémon não encontrado no time.";
    }

    public String updatePokemonInTrainerTeam(int trainerId, int oldPokemonId, Pokemon newPokemon) throws SQLException {
        String removeResult = removePokemonFromTrainerTeam(trainerId, oldPokemonId);

        if (removeResult.contains("sucesso")) {
            String addResult = addPokemonToTrainerTeam(trainerId, newPokemon);
            return "Time atualizado: " + removeResult + " e " + addResult;
        } else {
            return "Erro ao atualizar o time: Pokémon antigo não encontrado no time.";
        }
    }
}
