package br.com.fiap.bo;

import br.com.fiap.dao.DentistaDAO;
import br.com.fiap.entities.Dentista;

import java.sql.SQLException;
import java.util.ArrayList;

public class DentistaBO {

    DentistaDAO dentistaDAO;

    // ----------------------------------------------------------------
    // Selecionar todos
    // ----------------------------------------------------------------
    public ArrayList<Dentista> selecionarBo() throws ClassNotFoundException, SQLException {
        dentistaDAO = new DentistaDAO();
        // Regra de negócio: retorna lista completa ordenada por nome
        return (ArrayList<Dentista>) dentistaDAO.selecionar();
    }

    // ----------------------------------------------------------------
    // Buscar por ID
    // ----------------------------------------------------------------
    public Dentista buscarPorIdBo(int id) throws SQLException, ClassNotFoundException {
        dentistaDAO = new DentistaDAO();
        // Regra de negócio: valida ID positivo antes de consultar
        if (id <= 0) {
            throw new IllegalArgumentException("ID do dentista deve ser maior que zero.");
        }
        return dentistaDAO.buscarPorId(id);
    }

    // ----------------------------------------------------------------
    // Inserir
    // ----------------------------------------------------------------
    public void inserirBo(Dentista dentista) throws ClassNotFoundException, SQLException {
        dentistaDAO = new DentistaDAO();

        // Regra de negócio: nome e CRO são obrigatórios
        if (dentista.getNome() == null || dentista.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do dentista é obrigatório.");
        }
        if (dentista.getCro() == null || dentista.getCro().trim().isEmpty()) {
            throw new IllegalArgumentException("CRO do dentista é obrigatório.");
        }
        // Regra de negócio: CRO deve ter entre 5 e 20 caracteres
        if (dentista.getCro().trim().length() < 5 || dentista.getCro().trim().length() > 20) {
            throw new IllegalArgumentException("CRO inválido. Deve ter entre 5 e 20 caracteres.");
        }

        dentistaDAO.inserir(dentista);
    }

    // ----------------------------------------------------------------
    // Atualizar
    // ----------------------------------------------------------------
    public void atualizarBo(Dentista dentista) throws ClassNotFoundException, SQLException {
        dentistaDAO = new DentistaDAO();

        // Regra de negócio: ID deve ser válido
        if (dentista.getId() <= 0) {
            throw new IllegalArgumentException("ID inválido para atualização.");
        }
        // Regra de negócio: nome é obrigatório
        if (dentista.getNome() == null || dentista.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do dentista é obrigatório.");
        }

        dentistaDAO.atualizar(dentista);
    }

    // ----------------------------------------------------------------
    // Deletar
    // ----------------------------------------------------------------
    public void deletarBo(int id) throws ClassNotFoundException, SQLException {
        dentistaDAO = new DentistaDAO();

        // Regra de negócio: não permite deletar ID inválido
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para exclusão.");
        }

        dentistaDAO.deletar(id);
    }
}
