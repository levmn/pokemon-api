package br.com.fiap.app;

import br.com.fiap.bo.PokemonBO;
import br.com.fiap.model.vo.Pokemon;

import java.util.List;

public class PokemonMain {
    private static final PokemonBO pokemonBO = new PokemonBO();

    public static void main(String[] args) {
        // Teste 1: Buscar todos os Pokémons da PokeAPI
        System.out.println("Testando buscar todos os Pokémons...");
        try {
            List<Pokemon> allPokemons = pokemonBO.getAllPokemon();
            if (allPokemons.isEmpty()) {
                System.out.println("Nenhum Pokémon encontrado.");
            } else {
                System.out.println("Pokémons encontrados na PokeAPI:");
                for (Pokemon p : allPokemons) {
                    System.out.println(p);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar todos os Pokémons: " + e.getMessage());
        }

        // Teste 2: Buscar um Pokémon por nome
        System.out.println("\nTestando buscar Pokémon por nome...");
        try {
            String pokemonName = "pikachu";  // Aqui você pode substituir pelo nome de um Pokémon
            Pokemon pokemon = pokemonBO.getPokemonByName(pokemonName);
            System.out.println("Pokémon encontrado: " + pokemon);
        } catch (Exception e) {
            System.err.println("Erro ao buscar Pokémon pelo nome: " + e.getMessage());
        }

        // Teste 3: Adicionar um Pokémon no banco de dados
        System.out.println("\nTestando adicionar Pokémon ao banco...");
        try {
            int pokemonIdToAdd = 25; // Exemplo de ID para o Pokémon Pikachu
            pokemonBO.fetchAndSavePokemon(pokemonIdToAdd);
            System.out.println("Pokémon " + pokemonIdToAdd + " foi adicionado ao banco de dados com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao adicionar Pokémon: " + e.getMessage());
        }

        // Teste 4: Listar todos os Pokémons salvos no banco de dados
        System.out.println("\nTestando listar todos os Pokémons salvos...");
        try {
            List<Pokemon> savedPokemons = pokemonBO.listAllSavedPokemons();
            if (savedPokemons.isEmpty()) {
                System.out.println("Nenhum Pokémon salvo encontrado.");
            } else {
                System.out.println("Pokémons salvos no banco de dados:");
                for (Pokemon p : savedPokemons) {
                    System.out.println(p);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar Pokémons salvos: " + e.getMessage());
        }
    }
}
