package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.Agendamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {

    public Connection minhaConexao;

    public AgendamentoDAO() throws SQLException, ClassNotFoundException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // INSERT
    public String inserir(Agendamento agendamento) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO T_BC_AGENDAMENTO " +
                "(ID, NOME_PACIENTE, RESPONSAVEL, TELEFONE, CIDADE, DATA_PREFERIDA, PERIODO, MOTIVO, STATUS, CRIADO_EM) " +
                "VALUES (SEQ_BC_AGENDAMENTO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, 'Pendente', SYSDATE)"
        );
        stmt.setString(1, agendamento.getNomePaciente());
        stmt.setString(2, agendamento.getResponsavel());
        stmt.setString(3, agendamento.getTelefone());
        stmt.setString(4, agendamento.getCidade());
        stmt.setString(5, agendamento.getDataPreferida());
        stmt.setString(6, agendamento.getPeriodo());
        stmt.setString(7, agendamento.getMotivo());

        stmt.execute();
        stmt.close();
        return "Agendamento solicitado com sucesso!";
    }

    // DELETE
    public String deletar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "DELETE FROM T_BC_AGENDAMENTO WHERE ID = ?"
        );
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        return "Agendamento removido com sucesso!";
    }

    // UPDATE STATUS (Confirmar / Recusar)
    public String atualizarStatus(int id, String status) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE T_BC_AGENDAMENTO SET STATUS = ? WHERE ID = ?"
        );
        stmt.setString(1, status);
        stmt.setInt(2, id);
        stmt.executeUpdate();
        stmt.close();
        return "Status atualizado para: " + status;
    }

    // UPDATE FULL
    public String atualizar(Agendamento agendamento) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE T_BC_AGENDAMENTO SET NOME_PACIENTE = ?, RESPONSAVEL = ?, TELEFONE = ?, " +
                "CIDADE = ?, DATA_PREFERIDA = ?, PERIODO = ?, MOTIVO = ?, STATUS = ? WHERE ID = ?"
        );
        stmt.setString(1, agendamento.getNomePaciente());
        stmt.setString(2, agendamento.getResponsavel());
        stmt.setString(3, agendamento.getTelefone());
        stmt.setString(4, agendamento.getCidade());
        stmt.setString(5, agendamento.getDataPreferida());
        stmt.setString(6, agendamento.getPeriodo());
        stmt.setString(7, agendamento.getMotivo());
        stmt.setString(8, agendamento.getStatus());
        stmt.setInt(9, agendamento.getId());

        stmt.executeUpdate();
        stmt.close();
        return "Agendamento atualizado com sucesso!";
    }

    // SELECT ALL
    public List<Agendamento> selecionar() throws SQLException {
        List<Agendamento> listaAgendamentos = new ArrayList<>();
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM T_BC_AGENDAMENTO ORDER BY CRIADO_EM DESC"
        );
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Agendamento ag = mapearResultSet(rs);
            listaAgendamentos.add(ag);
        }
        rs.close();
        stmt.close();
        return listaAgendamentos;
    }

    // SELECT BY ID
    public Agendamento buscarPorId(int id) throws SQLException {
        Agendamento agendamento = null;
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM T_BC_AGENDAMENTO WHERE ID = ?"
        );
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            agendamento = mapearResultSet(rs);
        }
        rs.close();
        stmt.close();
        return agendamento;
    }

    // SELECT BY STATUS
    public List<Agendamento> selecionarPorStatus(String status) throws SQLException {
        List<Agendamento> lista = new ArrayList<>();
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM T_BC_AGENDAMENTO WHERE STATUS = ? ORDER BY CRIADO_EM DESC"
        );
        stmt.setString(1, status);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            lista.add(mapearResultSet(rs));
        }
        rs.close();
        stmt.close();
        return lista;
    }

    private Agendamento mapearResultSet(ResultSet rs) throws SQLException {
        Agendamento ag = new Agendamento();
        ag.setId(rs.getInt("ID"));
        ag.setNomePaciente(rs.getString("NOME_PACIENTE"));
        ag.setResponsavel(rs.getString("RESPONSAVEL"));
        ag.setTelefone(rs.getString("TELEFONE"));
        ag.setCidade(rs.getString("CIDADE"));
        ag.setDataPreferida(rs.getString("DATA_PREFERIDA"));
        ag.setPeriodo(rs.getString("PERIODO"));
        ag.setMotivo(rs.getString("MOTIVO"));
        ag.setStatus(rs.getString("STATUS"));
        ag.setCriadoEm(rs.getString("CRIADO_EM"));
        return ag;
    }
}
