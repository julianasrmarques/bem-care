package br.com.fiap.main;

import br.com.fiap.bo.PacienteBO;
import br.com.fiap.entities.Paciente;
import br.com.fiap.excecoes.ExcecoesConexao;

import java.util.ArrayList;

public class TesteSelecionarPaciente {

    public static void main(String[] args) {
        try {
            PacienteBO pacienteBO = new PacienteBO();

            ArrayList<Paciente> lista = pacienteBO.selecionarBo();
            System.out.println("Total de pacientes encontrados: " + lista.size());
            System.out.println("-------------------------------");
            for (Paciente p : lista) {
                System.out.println(p);
            }

        } catch (Exception e) {
            new ExcecoesConexao(e);
        }
    }
}
