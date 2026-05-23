package br.com.fiap.main;

import br.com.fiap.bo.AgendamentoBO;
import br.com.fiap.entities.Agendamento;
import br.com.fiap.excecoes.ExcecoesConexao;

import java.util.ArrayList;

public class TesteSelecionarAgendamento {

    public static void main(String[] args) {
        try {
            AgendamentoBO agendamentoBO = new AgendamentoBO();

            // Listar todos
            ArrayList<Agendamento> lista = agendamentoBO.selecionarBo();
            System.out.println("Total de agendamentos: " + lista.size());
            System.out.println("-------------------------------");
            for (Agendamento a : lista) {
                System.out.println(a);
            }

            // Listar apenas pendentes (método lógico de negócio)
            System.out.println("\n=== Agendamentos PENDENTES ===");
            ArrayList<Agendamento> pendentes = agendamentoBO.buscarPorStatusBo("Pendente");
            System.out.println("Pendentes: " + pendentes.size());
            for (Agendamento a : pendentes) {
                System.out.println(a);
            }

        } catch (Exception e) {
            new ExcecoesConexao(e);
        }
    }
}
