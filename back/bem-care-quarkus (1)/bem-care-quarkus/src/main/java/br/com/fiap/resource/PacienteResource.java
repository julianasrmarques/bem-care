package br.com.fiap.resource;

import br.com.fiap.bo.PacienteBO;
import br.com.fiap.entities.Paciente;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/paciente")
public class PacienteResource {

    private PacienteBO pacienteBO = new PacienteBO();

    // GET /paciente — listar todos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarRs() {
        try {
            ArrayList<Paciente> lista = pacienteBO.selecionarBo();
            return Response.ok(lista).build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar pacientes: " + e.getMessage()).build();
        }
    }

    // GET /paciente/{id} — buscar por ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorIdRs(@PathParam("id") int id) {
        try {
            Paciente paciente = pacienteBO.buscarPorIdBo(id);
            if (paciente == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Paciente não encontrado para o ID: " + id).build();
            }
            return Response.ok(paciente).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar paciente: " + e.getMessage()).build();
        }
    }

    // POST /paciente — inserir
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirRs(Paciente paciente, @Context UriInfo uriInfo) {
        try {
            pacienteBO.inserirBo(paciente);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(paciente.getId()));
            return Response.created(builder.build()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao inserir paciente: " + e.getMessage()).build();
        }
    }

    // PUT /paciente — atualizar
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarRs(Paciente paciente) {
        try {
            pacienteBO.atualizarBo(paciente);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar paciente: " + e.getMessage()).build();
        }
    }

    // DELETE /paciente/{id} — deletar
    @DELETE
    @Path("/{id}")
    public Response deletarRs(@PathParam("id") int id) {
        try {
            pacienteBO.deletarBo(id);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar paciente: " + e.getMessage()).build();
        }
    }
}
