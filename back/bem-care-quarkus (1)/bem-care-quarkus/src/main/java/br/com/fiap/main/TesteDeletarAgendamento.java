package br.com.fiap.main;

import br.com.fiap.bo.AgendamentoBO;
import br.com.fiap.excecoes.ExcecoesConexao;

public class TesteDeletarAgendamento {

    public static void main(String[] args) {
        try {
            AgendamentoBO agendamentoBO = new AgendamentoBO();

            // Altere o ID para o agendamento que deseja deletar
            int idParaDeletar = 1;

            agendamentoBO.deletarBo(idParaDeletar);
            System.out.println("Agendamento com ID " + idParaDeletar + " removido com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            new ExcecoesConexao(e);
        }
    }
}
