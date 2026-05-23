package br.com.fiap.main;

import br.com.fiap.bo.DentistaBO;
import br.com.fiap.bo.PacienteBO;
import br.com.fiap.entities.Dentista;
import br.com.fiap.entities.Paciente;
import br.com.fiap.excecoes.ExcecoesConexao;

import java.util.ArrayList;

public class TesteArrayListDentista {

    public static void main(String[] args) {
        try {
            DentistaBO dentistaBO = new DentistaBO();
            PacienteBO pacienteBO = new PacienteBO();

            // Lista todos os dentistas
            ArrayList<Dentista> dentistas = dentistaBO.selecionarBo();
            System.out.println("===== DENTISTAS =====");
            for (int i = 0; i < dentistas.size(); i++) {
                System.out.println("[" + i + "] " + dentistas.get(i));
            }

            System.out.println();

            // Lista todos os pacientes
            ArrayList<Paciente> pacientes = pacienteBO.selecionarBo();
            System.out.println("===== PACIENTES =====");
            for (int i = 0; i < pacientes.size(); i++) {
                System.out.println("[" + i + "] " + pacientes.get(i));
            }

        } catch (Exception e) {
            new ExcecoesConexao(e);
        }
    }
}
