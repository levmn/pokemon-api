package br.com.fiap.resources;

import br.com.fiap.bo.PokemonBO;
import br.com.fiap.model.vo.Pokemon;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("pokemon")
public class PokemonResource {

    private final PokemonBO pokemonBO = new PokemonBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPokemon() {
        try {
            List<Pokemon> pokemonList = pokemonBO.getAllPokemon();
            return Response.ok(pokemonList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("saved")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllSavedPokemons() {
        try {
            List<Pokemon> savedPokemons = pokemonBO.listAllSavedPokemons();
            return Response.ok(savedPokemons).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPokemon(@PathParam("name") String name) {
        try {
            Pokemon pokemon = pokemonBO.getPokemonByName(name);
            return Response.ok(pokemon).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("add/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response fetchAndAddPokemon(@PathParam("id") int id) {
        try {
            pokemonBO.fetchAndSavePokemon(id);
            return Response.ok("Dados do seu Pokémon foram inseridos com sucesso!").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response removePokemon(@PathParam("id") int id) {
        try {
            pokemonBO.removePokemon(id);
            return Response.ok("Pokémon com ID: " + id + " removido com sucesso!").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
