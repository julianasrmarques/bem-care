package br.com.fiap.bo;

import br.com.fiap.dao.PacienteDAO;
import br.com.fiap.entities.Paciente;

import java.sql.SQLException;
import java.util.ArrayList;

public class PacienteBO {

    PacienteDAO pacienteDAO;

    // ----------------------------------------------------------------
    // Selecionar todos
    // ----------------------------------------------------------------
    public ArrayList<Paciente> selecionarBo() throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();
        // Regra de negócio: retorna todos os pacientes ativos
        return (ArrayList<Paciente>) pacienteDAO.selecionar();
    }

    // ----------------------------------------------------------------
    // Buscar por ID
    // ----------------------------------------------------------------
    public Paciente buscarPorIdBo(int id) throws SQLException, ClassNotFoundException {
        pacienteDAO = new PacienteDAO();
        // Regra de negócio: ID deve ser positivo
        if (id <= 0) {
            throw new IllegalArgumentException("ID do paciente deve ser maior que zero.");
        }
        return pacienteDAO.buscarPorId(id);
    }

    // ----------------------------------------------------------------
    // Inserir
    // ----------------------------------------------------------------
    public void inserirBo(Paciente paciente) throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();

        // Regra de negócio: nome é obrigatório
        if (paciente.getNome() == null || paciente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do paciente é obrigatório.");
        }
        // Regra de negócio: pacientes atendidos pela ONG têm até 18 anos
        if (paciente.getIdade() < 0 || paciente.getIdade() > 18) {
            throw new IllegalArgumentException("Idade inválida. A ONG atende crianças e adolescentes de 0 a 18 anos.");
        }
        // Regra de negócio: responsável é obrigatório para menores
        if (paciente.getIdade() < 18 && (paciente.getResponsavel() == null || paciente.getResponsavel().trim().isEmpty())) {
            throw new IllegalArgumentException("Responsável é obrigatório para pacientes menores de 18 anos.");
        }
        // Regra de negócio: telefone deve ter pelo menos 10 dígitos
        if (paciente.getTelefone() == null || paciente.getTelefone().replaceAll("[^0-9]", "").length() < 10) {
            throw new IllegalArgumentException("Telefone inválido. Informe DDD + número (mínimo 10 dígitos).");
        }

        pacienteDAO.inserir(paciente);
    }

    // ----------------------------------------------------------------
    // Atualizar
    // ----------------------------------------------------------------
    public void atualizarBo(Paciente paciente) throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();

        if (paciente.getId() <= 0) {
            throw new IllegalArgumentException("ID inválido para atualização.");
        }
        if (paciente.getNome() == null || paciente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do paciente é obrigatório.");
        }

        pacienteDAO.atualizar(paciente);
    }

    // ----------------------------------------------------------------
    // Deletar
    // ----------------------------------------------------------------
    public void deletarBo(int id) throws ClassNotFoundException, SQLException {
        pacienteDAO = new PacienteDAO();

        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para exclusão.");
        }

        pacienteDAO.deletar(id);
    }
}
