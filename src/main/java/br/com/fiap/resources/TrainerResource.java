package br.com.fiap.resources;

import br.com.fiap.bo.TrainerBO;
import br.com.fiap.model.vo.Pokemon;
import br.com.fiap.model.vo.Trainer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("trainer")
public class TrainerResource {

    private final TrainerBO trainerBO = new TrainerBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response registerTrainer(Trainer trainer) {
        try {
            String result = trainerBO.registerTrainer(trainer);
            return Response.status(Response.Status.CREATED).entity(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrainerById(@PathParam("id") int id) {
        try {
            Trainer trainer = trainerBO.getTrainerById(id);
            return Response.ok(trainer).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("{trainerId}/add-pokemon")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addPokemonToTeam(@PathParam("trainerId") int trainerId, Pokemon pokemon) {
        try {
            String result = trainerBO.addPokemonToTrainerTeam(trainerId, pokemon);
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{trainerId}/remove-pokemon/{pokemonId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response removePokemonFromTeam(@PathParam("trainerId") int trainerId, @PathParam("pokemonId") int pokemonId) {
        try {
            String result = trainerBO.removePokemonFromTrainerTeam(trainerId, pokemonId);
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("{trainerId}/update-pokemon/{oldPokemonId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updatePokemonInTeam(@PathParam("trainerId") int trainerId,
                                        @PathParam("oldPokemonId") int oldPokemonId,
                                        Pokemon newPokemon) {
        try {
            String result = trainerBO.updatePokemonInTrainerTeam(trainerId, oldPokemonId, newPokemon);
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
