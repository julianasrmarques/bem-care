package br.com.fiap.main;

import br.com.fiap.bo.PacienteBO;
import br.com.fiap.entities.Paciente;
import br.com.fiap.excecoes.ExcecoesConexao;

public class TesteInserirPaciente {

    public static void main(String[] args) {
        try {
            PacienteBO pacienteBO = new PacienteBO();

            Paciente paciente = new Paciente();
            paciente.setNome("Pedro Henrique");
            paciente.setIdade(10);
            paciente.setResponsavel("Carlos Henrique");
            paciente.setCidade("São Paulo");
            paciente.setTelefone("(11) 98888-1234");

            pacienteBO.inserirBo(paciente);
            System.out.println("Paciente inserido com sucesso!");
            System.out.println(paciente);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            new ExcecoesConexao(e);
        }
    }
}
