package br.com.fiap.resource;

import br.com.fiap.bo.DentistaBO;
import br.com.fiap.entities.Dentista;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/dentista")
public class DentistaResource {

    private DentistaBO dentistaBO = new DentistaBO();

    // GET /dentista — listar todos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarRs() {
        try {
            ArrayList<Dentista> lista = dentistaBO.selecionarBo();
            return Response.ok(lista).build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar dentistas: " + e.getMessage()).build();
        }
    }

    // GET /dentista/{id} — buscar por ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorIdRs(@PathParam("id") int id) {
        try {
            Dentista dentista = dentistaBO.buscarPorIdBo(id);
            if (dentista == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Dentista não encontrado para o ID: " + id).build();
            }
            return Response.ok(dentista).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar dentista: " + e.getMessage()).build();
        }
    }

    // POST /dentista — inserir
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirRs(Dentista dentista, @Context UriInfo uriInfo) {
        try {
            dentistaBO.inserirBo(dentista);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(dentista.getId()));
            return Response.created(builder.build()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao inserir dentista: " + e.getMessage()).build();
        }
    }

    // PUT /dentista — atualizar
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarRs(Dentista dentista) {
        try {
            dentistaBO.atualizarBo(dentista);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar dentista: " + e.getMessage()).build();
        }
    }

    // DELETE /dentista/{id} — deletar
    @DELETE
    @Path("/{id}")
    public Response deletarRs(@PathParam("id") int id) {
        try {
            dentistaBO.deletarBo(id);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar dentista: " + e.getMessage()).build();
        }
    }
}
