package br.com.fiap.bo;

import br.com.fiap.dao.AgendamentoDAO;
import br.com.fiap.entities.Agendamento;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AgendamentoBO {

    AgendamentoDAO agendamentoDAO;

    private static final List<String> PERIODOS_VALIDOS = Arrays.asList("Manhã", "Tarde", "Noite");
    private static final List<String> STATUS_VALIDOS   = Arrays.asList("Pendente", "Confirmado", "Recusado");

    // ----------------------------------------------------------------
    // Selecionar todos
    // ----------------------------------------------------------------
    public ArrayList<Agendamento> selecionarBo() throws ClassNotFoundException, SQLException {
        agendamentoDAO = new AgendamentoDAO();
        // Regra de negócio: retorna lista mais recente primeiro
        return (ArrayList<Agendamento>) agendamentoDAO.selecionar();
    }

    // ----------------------------------------------------------------
    // Buscar por ID
    // ----------------------------------------------------------------
    public Agendamento buscarPorIdBo(int id) throws SQLException, ClassNotFoundException {
        agendamentoDAO = new AgendamentoDAO();
        if (id <= 0) {
            throw new IllegalArgumentException("ID do agendamento deve ser maior que zero.");
        }
        return agendamentoDAO.buscarPorId(id);
    }

    // ----------------------------------------------------------------
    // Buscar por status (método lógico de negócio exigido)
    // Filtra agendamentos por status para facilitar o painel da ONG
    // ----------------------------------------------------------------
    public ArrayList<Agendamento> buscarPorStatusBo(String status) throws SQLException, ClassNotFoundException {
        agendamentoDAO = new AgendamentoDAO();
        // Regra de negócio: status deve ser um dos valores permitidos
        if (!STATUS_VALIDOS.contains(status)) {
            throw new IllegalArgumentException(
                    "Status inválido. Valores aceitos: " + String.join(", ", STATUS_VALIDOS));
        }
        return (ArrayList<Agendamento>) agendamentoDAO.selecionarPorStatus(status);
    }

    // ----------------------------------------------------------------
    // Inserir (solicitar agendamento)
    // ----------------------------------------------------------------
    public void inserirBo(Agendamento agendamento) throws ClassNotFoundException, SQLException {
        agendamentoDAO = new AgendamentoDAO();

        // Regra de negócio: campos obrigatórios
        if (agendamento.getNomePaciente() == null || agendamento.getNomePaciente().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do paciente é obrigatório.");
        }
        if (agendamento.getTelefone() == null || agendamento.getTelefone().replaceAll("[^0-9]", "").length() < 10) {
            throw new IllegalArgumentException("Telefone inválido. Informe DDD + número.");
        }
        if (agendamento.getDataPreferida() == null || agendamento.getDataPreferida().trim().isEmpty()) {
            throw new IllegalArgumentException("Data preferida é obrigatória.");
        }
        // Regra de negócio: período deve ser válido
        if (!PERIODOS_VALIDOS.contains(agendamento.getPeriodo())) {
            throw new IllegalArgumentException(
                    "Período inválido. Valores aceitos: " + String.join(", ", PERIODOS_VALIDOS));
        }

        agendamentoDAO.inserir(agendamento);
    }

    // ----------------------------------------------------------------
    // Atualizar status (Confirmar / Recusar)
    // Método lógico de negócio: regra de transição de estado
    // ----------------------------------------------------------------
    public void atualizarStatusBo(int id, String novoStatus) throws ClassNotFoundException, SQLException {
        agendamentoDAO = new AgendamentoDAO();

        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        // Regra de negócio: só aceita status permitidos
        if (!STATUS_VALIDOS.contains(novoStatus)) {
            throw new IllegalArgumentException(
                    "Status inválido. Valores aceitos: " + String.join(", ", STATUS_VALIDOS));
        }
        // Regra de negócio: não pode voltar para "Pendente" após decisão
        Agendamento atual = agendamentoDAO.buscarPorId(id);
        if (atual == null) {
            throw new IllegalArgumentException("Agendamento não encontrado para o ID: " + id);
        }
        if (!"Pendente".equals(atual.getStatus()) && "Pendente".equals(novoStatus)) {
            throw new IllegalArgumentException(
                    "Agendamento já foi decidido e não pode ser revertido para Pendente.");
        }

        agendamentoDAO.atualizarStatus(id, novoStatus);
    }

    // ----------------------------------------------------------------
    // Atualizar completo
    // ----------------------------------------------------------------
    public void atualizarBo(Agendamento agendamento) throws ClassNotFoundException, SQLException {
        agendamentoDAO = new AgendamentoDAO();
        if (agendamento.getId() <= 0) {
            throw new IllegalArgumentException("ID inválido para atualização.");
        }
        agendamentoDAO.atualizar(agendamento);
    }

    // ----------------------------------------------------------------
    // Deletar
    // ----------------------------------------------------------------
    public void deletarBo(int id) throws ClassNotFoundException, SQLException {
        agendamentoDAO = new AgendamentoDAO();
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para exclusão.");
        }
        agendamentoDAO.deletar(id);
    }
}
