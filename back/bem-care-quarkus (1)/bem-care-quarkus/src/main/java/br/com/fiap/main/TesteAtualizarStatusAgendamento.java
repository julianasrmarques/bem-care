package br.com.fiap.main;

import br.com.fiap.bo.AgendamentoBO;
import br.com.fiap.excecoes.ExcecoesConexao;

public class TesteAtualizarStatusAgendamento {

    public static void main(String[] args) {
        try {
            AgendamentoBO agendamentoBO = new AgendamentoBO();

            // Altere o ID para um agendamento existente no banco
            int idAgendamento = 1;
            String novoStatus = "Confirmado"; // "Confirmado" ou "Recusado"

            agendamentoBO.atualizarStatusBo(idAgendamento, novoStatus);
            System.out.println("Status do agendamento ID " + idAgendamento +
                               " atualizado para: " + novoStatus);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            new ExcecoesConexao(e);
        }
    }
}
