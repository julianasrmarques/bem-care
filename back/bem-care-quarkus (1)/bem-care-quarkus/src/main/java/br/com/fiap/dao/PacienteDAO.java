package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public Connection minhaConexao;

    public PacienteDAO() throws SQLException, ClassNotFoundException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // INSERT
    public String inserir(Paciente paciente) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO T_BC_PACIENTE (ID, NOME, IDADE, RESPONSAVEL, CIDADE, TELEFONE) " +
                "VALUES (SEQ_BC_PACIENTE.NEXTVAL, ?, ?, ?, ?, ?)"
        );
        stmt.setString(1, paciente.getNome());
        stmt.setInt(2, paciente.getIdade());
        stmt.setString(3, paciente.getResponsavel());
        stmt.setString(4, paciente.getCidade());
        stmt.setString(5, paciente.getTelefone());

        stmt.execute();
        stmt.close();
        return "Paciente cadastrado com sucesso!";
    }

    // DELETE
    public String deletar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "DELETE FROM T_BC_PACIENTE WHERE ID = ?"
        );
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        return "Paciente deletado com sucesso!";
    }

    // UPDATE
    public String atualizar(Paciente paciente) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE T_BC_PACIENTE SET NOME = ?, IDADE = ?, RESPONSAVEL = ?, CIDADE = ?, TELEFONE = ? WHERE ID = ?"
        );
        stmt.setString(1, paciente.getNome());
        stmt.setInt(2, paciente.getIdade());
        stmt.setString(3, paciente.getResponsavel());
        stmt.setString(4, paciente.getCidade());
        stmt.setString(5, paciente.getTelefone());
        stmt.setInt(6, paciente.getId());

        stmt.executeUpdate();
        stmt.close();
        return "Paciente atualizado com sucesso!";
    }

    // SELECT ALL
    public List<Paciente> selecionar() throws SQLException {
        List<Paciente> listaPacientes = new ArrayList<>();
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM T_BC_PACIENTE ORDER BY NOME"
        );
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Paciente paciente = new Paciente();
            paciente.setId(rs.getInt("ID"));
            paciente.setNome(rs.getString("NOME"));
            paciente.setIdade(rs.getInt("IDADE"));
            paciente.setResponsavel(rs.getString("RESPONSAVEL"));
            paciente.setCidade(rs.getString("CIDADE"));
            paciente.setTelefone(rs.getString("TELEFONE"));
            listaPacientes.add(paciente);
        }
        rs.close();
        stmt.close();
        return listaPacientes;
    }

    // SELECT BY ID
    public Paciente buscarPorId(int id) throws SQLException {
        Paciente paciente = null;
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM T_BC_PACIENTE WHERE ID = ?"
        );
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            paciente = new Paciente();
            paciente.setId(rs.getInt("ID"));
            paciente.setNome(rs.getString("NOME"));
            paciente.setIdade(rs.getInt("IDADE"));
            paciente.setResponsavel(rs.getString("RESPONSAVEL"));
            paciente.setCidade(rs.getString("CIDADE"));
            paciente.setTelefone(rs.getString("TELEFONE"));
        }
        rs.close();
        stmt.close();
        return paciente;
    }
}
