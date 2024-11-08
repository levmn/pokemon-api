package br.com.fiap.bo;

import br.com.fiap.model.dao.PokemonDAO;
import br.com.fiap.model.vo.Pokemon;
import br.com.fiap.service.PokemonService;

import java.util.List;

public class PokemonBO {
    private final PokemonService pokemonService = new PokemonService();
    private final PokemonDAO pokemonDAO = new PokemonDAO();

    public List<Pokemon> getAllPokemon() throws Exception {
        return pokemonService.fetchAllFromPokeAPI();
    }

    public void fetchAndSavePokemon(int id) throws Exception {
        Pokemon pokemon = pokemonService.fetchFromPokeAPI(id);

        if (!validatePokemonData(pokemon)) {
            throw new Exception("Erro: Não foi possível encontrar este Pokémon.");
        }

        pokemonDAO.insertPokemon(pokemon);
    }

    public List<Pokemon> listAllSavedPokemons() throws Exception {
        return pokemonDAO.getListPokemon();
    }

    public Pokemon getPokemonByName(String name) throws Exception {
        Pokemon pokemon = pokemonService.fetchByNameFromPokeAPI(name);
        if (pokemon == null) {
            throw new Exception("Erro: Pokémon não encontrado.");
        }

        return pokemon;
    }

    public void removePokemon(int id) throws Exception {

        if (pokemonDAO.getPokemonById(id) == null) {
            throw new IllegalArgumentException("Erro: Pokémon não encontrado para o ID fornecido.");
        }

        pokemonDAO.removePokemon(id);
    }

    private boolean validatePokemonData(Pokemon pokemon) {
        if (pokemon == null) return false;
        if (pokemon.getId() <= 0) return false;
        if (pokemon.getName() == null || pokemon.getName().isEmpty()) return false;
        if (pokemon.getTypes() == null || pokemon.getTypes().isEmpty()) return false;
        if (pokemon.getAbilities() == null || pokemon.getAbilities().isEmpty()) return false;
        return true;
    }
}
