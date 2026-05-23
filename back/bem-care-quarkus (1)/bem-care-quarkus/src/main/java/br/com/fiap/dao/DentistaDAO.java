package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.Dentista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DentistaDAO {

    public Connection minhaConexao;

    public DentistaDAO() throws SQLException, ClassNotFoundException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // INSERT
    public String inserir(Dentista dentista) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO T_BC_DENTISTA (ID, NOME, CRO, ESPECIALIDADE, EMAIL, CIDADE) " +
                "VALUES (SEQ_BC_DENTISTA.NEXTVAL, ?, ?, ?, ?, ?)"
        );
        stmt.setString(1, dentista.getNome());
        stmt.setString(2, dentista.getCro());
        stmt.setString(3, dentista.getEspecialidade());
        stmt.setString(4, dentista.getEmail());
        stmt.setString(5, dentista.getCidade());

        stmt.execute();
        stmt.close();
        return "Dentista cadastrado com sucesso!";
    }

    // DELETE
    public String deletar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "DELETE FROM T_BC_DENTISTA WHERE ID = ?"
        );
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        return "Dentista deletado com sucesso!";
    }

    // UPDATE
    public String atualizar(Dentista dentista) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE T_BC_DENTISTA SET NOME = ?, CRO = ?, ESPECIALIDADE = ?, EMAIL = ?, CIDADE = ? WHERE ID = ?"
        );
        stmt.setString(1, dentista.getNome());
        stmt.setString(2, dentista.getCro());
        stmt.setString(3, dentista.getEspecialidade());
        stmt.setString(4, dentista.getEmail());
        stmt.setString(5, dentista.getCidade());
        stmt.setInt(6, dentista.getId());

        stmt.executeUpdate();
        stmt.close();
        return "Dentista atualizado com sucesso!";
    }

    // SELECT ALL
    public List<Dentista> selecionar() throws SQLException {
        List<Dentista> listaDentistas = new ArrayList<>();
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM T_BC_DENTISTA ORDER BY NOME"
        );
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Dentista dentista = new Dentista();
            dentista.setId(rs.getInt("ID"));
            dentista.setNome(rs.getString("NOME"));
            dentista.setCro(rs.getString("CRO"));
            dentista.setEspecialidade(rs.getString("ESPECIALIDADE"));
            dentista.setEmail(rs.getString("EMAIL"));
            dentista.setCidade(rs.getString("CIDADE"));
            listaDentistas.add(dentista);
        }
        rs.close();
        stmt.close();
        return listaDentistas;
    }

    // SELECT BY ID
    public Dentista buscarPorId(int id) throws SQLException {
        Dentista dentista = null;
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM T_BC_DENTISTA WHERE ID = ?"
        );
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            dentista = new Dentista();
            dentista.setId(rs.getInt("ID"));
            dentista.setNome(rs.getString("NOME"));
            dentista.setCro(rs.getString("CRO"));
            dentista.setEspecialidade(rs.getString("ESPECIALIDADE"));
            dentista.setEmail(rs.getString("EMAIL"));
            dentista.setCidade(rs.getString("CIDADE"));
        }
        rs.close();
        stmt.close();
        return dentista;
    }
}
