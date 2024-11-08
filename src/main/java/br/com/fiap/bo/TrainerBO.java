package br.com.fiap.bo;

import br.com.fiap.model.dao.TrainerDAO;
import br.com.fiap.model.vo.Pokemon;
import br.com.fiap.model.vo.Trainer;

import java.sql.SQLException;
import java.util.List;

public class TrainerBO {
    private final TrainerDAO trainerDAO = new TrainerDAO();

    public String registerTrainer(Trainer trainer) throws Exception {
        if (!validateTrainerName(trainer.getName())) {
            throw new Exception("Nome do treinador é inválido.");
        }
        return trainerDAO.insertTrainer(trainer);
    }

    public Trainer getTrainerById(int id) throws Exception {
        Trainer trainer = trainerDAO.getTrainerById(id);
        if (trainer == null) {
            throw new Exception("Erro: Trainer não encontrado.");
        }

        return trainer;
    }

    public String addPokemonToTrainerTeam(int trainerId, Pokemon pokemon) throws Exception {
        Trainer trainer = trainerDAO.getTrainerById(trainerId);
        if (trainer == null) {
            throw new Exception("Erro: Treinador não encontrado.");
        }

        if (isTeamFull(trainer)) {
            throw new Exception("Erro: O time já possui 3 Pokémons. Não é possível adicionar mais.");
        }

        if (isPokemonInTeam(trainer, pokemon.getId())) {
            throw new Exception("Erro: Esse Pokémon já está no time do treinador.");
        }

        return trainerDAO.addPokemonToTrainerTeam(trainerId, pokemon);
    }

    public String removePokemonFromTrainerTeam(int trainerId, int pokemonId) throws Exception {
        Trainer trainer = trainerDAO.getTrainerById(trainerId);
        if (trainer == null) {
            throw new Exception("Erro: Treinador não encontrado.");
        }

        if (!isPokemonInTeam(trainer, pokemonId)) {
            throw new Exception("Erro: O Pokémon não está no time do treinador.");
        }

        return trainerDAO.removePokemonFromTrainerTeam(trainerId, pokemonId);
    }

    public String updatePokemonInTrainerTeam(int trainerId, int oldPokemonId, Pokemon newPokemon) throws Exception {
        Trainer trainer = trainerDAO.getTrainerById(trainerId);
        if (trainer == null) {
            throw new Exception("Erro: Treinador não encontrado.");
        }

        if (!isPokemonInTeam(trainer, oldPokemonId)) {
            throw new Exception("Erro: O Pokémon que está sendo substituído não está no time do treinador.");
        }

        if (isPokemonInTeam(trainer, newPokemon.getId())) {
            throw new Exception("Erro: O novo Pokémon já está no time do treinador.");
        }

        return trainerDAO.updatePokemonInTrainerTeam(trainerId, oldPokemonId, newPokemon);
    }

    private boolean validateTrainerName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    private boolean isTeamFull(Trainer trainer) throws SQLException {
        List<Pokemon> team = trainerDAO.getTrainerTeam(trainer.getId());
        return team.size() >= 3;
    }

    private boolean isPokemonInTeam(Trainer trainer, int pokemonId) throws SQLException {
        List<Pokemon> team = trainerDAO.getTrainerTeam(trainer.getId());
        return team.stream().anyMatch(pokemon -> pokemon.getId() == pokemonId);
    }
}
