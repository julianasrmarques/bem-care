package br.com.fiap.resource;

import br.com.fiap.bo.AgendamentoBO;
import br.com.fiap.entities.Agendamento;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

@Path("/agendamento")
public class AgendamentoResource {

    private AgendamentoBO agendamentoBO = new AgendamentoBO();

    // GET /agendamento — listar todos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarRs() {
        try {
            ArrayList<Agendamento> lista = agendamentoBO.selecionarBo();
            return Response.ok(lista).build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar agendamentos: " + e.getMessage()).build();
        }
    }

    // GET /agendamento/{id} — buscar por ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorIdRs(@PathParam("id") int id) {
        try {
            Agendamento agendamento = agendamentoBO.buscarPorIdBo(id);
            if (agendamento == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Agendamento não encontrado para o ID: " + id).build();
            }
            return Response.ok(agendamento).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar agendamento: " + e.getMessage()).build();
        }
    }

    // GET /agendamento/status/{status} — filtrar por status
    @GET
    @Path("/status/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorStatusRs(@PathParam("status") String status) {
        try {
            ArrayList<Agendamento> lista = agendamentoBO.buscarPorStatusBo(status);
            return Response.ok(lista).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar agendamentos por status: " + e.getMessage()).build();
        }
    }

    // POST /agendamento — solicitar agendamento
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirRs(Agendamento agendamento, @Context UriInfo uriInfo) {
        try {
            agendamentoBO.inserirBo(agendamento);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(agendamento.getId()));
            return Response.created(builder.build()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao criar agendamento: " + e.getMessage()).build();
        }
    }

    // PUT /agendamento — atualizar completo
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarRs(Agendamento agendamento) {
        try {
            agendamentoBO.atualizarBo(agendamento);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar agendamento: " + e.getMessage()).build();
        }
    }

    // PATCH /agendamento/{id}/status — atualizar apenas o status
    @PATCH
    @Path("/{id}/status")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarStatusRs(@PathParam("id") int id, Map<String, String> body) {
        try {
            String novoStatus = body.get("status");
            if (novoStatus == null || novoStatus.trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Campo 'status' é obrigatório no corpo da requisição.").build();
            }
            agendamentoBO.atualizarStatusBo(id, novoStatus);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar status: " + e.getMessage()).build();
        }
    }

    // DELETE /agendamento/{id} — remover
    @DELETE
    @Path("/{id}")
    public Response deletarRs(@PathParam("id") int id) {
        try {
            agendamentoBO.deletarBo(id);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar agendamento: " + e.getMessage()).build();
        }
    }
}
