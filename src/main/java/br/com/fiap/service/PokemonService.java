package br.com.fiap.service;

import br.com.fiap.model.vo.Pokemon;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PokemonService {
    public List<Pokemon> fetchAllFromPokeAPI() throws Exception {
        URL url = new URL("https://pokeapi.co/api/v2/pokemon?limit=1000");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 200) {
            JsonObject json = JsonParser.parseReader(new InputStreamReader(conn.getInputStream())).getAsJsonObject();
            JsonArray resultsArray = json.getAsJsonArray("results");

            List<Pokemon> pokemonList = new ArrayList<>();
            for (int i = 0; i < resultsArray.size(); i++) {
                JsonObject pokemonJson = resultsArray.get(i).getAsJsonObject();
                String name = pokemonJson.get("name").getAsString();
                int id = i + 1;

                Pokemon pokemon = new Pokemon();
                pokemon.setId(id);
                pokemon.setName(name);
                pokemonList.add(pokemon);
            }

            return pokemonList;
        } else {
            throw new Exception("Erro: Falha ao buscar lista de Pokémons da PokeAPI.");
        }
    }

    public Pokemon fetchFromPokeAPI(int id) throws Exception {
        URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 200) {
            JsonObject json = JsonParser.parseReader(new InputStreamReader(conn.getInputStream())).getAsJsonObject();

            int pokemonId = id;
            String name = json.get("name").getAsString();

            JsonArray typesArray = json.getAsJsonArray("types");
            String types = StreamSupport.stream(typesArray.spliterator(), false)
                    .map(element -> element.getAsJsonObject().get("type").getAsJsonObject().get("name").getAsString())
                    .collect(Collectors.joining(", "));

            JsonArray abilitiesArray = json.getAsJsonArray("abilities");
            String abilities = StreamSupport.stream(abilitiesArray.spliterator(), false)
                    .map(element -> element.getAsJsonObject().get("ability").getAsJsonObject().get("name").getAsString())
                    .collect(Collectors.joining(", "));

            return new Pokemon(pokemonId, name, types, abilities);
        } else {
            throw new Exception("Erro: Falha ao buscar dados da PokeAPI.");
        }
    }

    public Pokemon fetchByNameFromPokeAPI(String name) throws Exception {
        URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + name);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 200) {
            JsonObject json = JsonParser.parseReader(new InputStreamReader(conn.getInputStream())).getAsJsonObject();

            int pokemonId = json.get("id").getAsInt();
            String pokemonName = json.get("name").getAsString();

            JsonArray typesArray = json.getAsJsonArray("types");
            String types = StreamSupport.stream(typesArray.spliterator(), false)
                    .map(element -> element.getAsJsonObject().get("type").getAsJsonObject().get("name").getAsString())
                    .collect(Collectors.joining(", "));

            JsonArray abilitiesArray = json.getAsJsonArray("abilities");
            String abilities = StreamSupport.stream(abilitiesArray.spliterator(), false)
                    .map(element -> element.getAsJsonObject().get("ability").getAsJsonObject().get("name").getAsString())
                    .collect(Collectors.joining(", "));

            return new Pokemon(pokemonId, pokemonName, types, abilities);
        } else {
            throw new Exception("Erro: Pokémon não encontrado, verifique se digitou o nome corretamente!");
        }
    }
}
