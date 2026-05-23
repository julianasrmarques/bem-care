package br.com.fiap.main;

import br.com.fiap.bo.AgendamentoBO;
import br.com.fiap.entities.Agendamento;
import br.com.fiap.excecoes.ExcecoesConexao;

public class TesteInserirAgendamento {

    public static void main(String[] args) {
        try {
            AgendamentoBO agendamentoBO = new AgendamentoBO();

            Agendamento agendamento = new Agendamento();
            agendamento.setNomePaciente("Joana da Silva");
            agendamento.setResponsavel("Maria da Silva");
            agendamento.setTelefone("(11) 96666-4321");
            agendamento.setCidade("São Paulo");
            agendamento.setDataPreferida("2025-07-15");
            agendamento.setPeriodo("Manhã");
            agendamento.setMotivo("Dor de dente e consulta preventiva");

            agendamentoBO.inserirBo(agendamento);
            System.out.println("Agendamento solicitado com sucesso!");
            System.out.println(agendamento);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            new ExcecoesConexao(e);
        }
    }
}
