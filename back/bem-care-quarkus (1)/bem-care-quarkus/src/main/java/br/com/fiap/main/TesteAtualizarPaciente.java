package br.com.fiap.main;

import br.com.fiap.bo.PacienteBO;
import br.com.fiap.entities.Paciente;
import br.com.fiap.excecoes.ExcecoesConexao;

public class TesteAtualizarPaciente {

    public static void main(String[] args) {
        try {
            PacienteBO pacienteBO = new PacienteBO();

            // Altere o ID para um existente no banco
            Paciente paciente = new Paciente();
            paciente.setId(1);
            paciente.setNome("Pedro Henrique Atualizado");
            paciente.setIdade(11);
            paciente.setResponsavel("Carlos Henrique");
            paciente.setCidade("Guarulhos");
            paciente.setTelefone("(11) 97777-5678");

            pacienteBO.atualizarBo(paciente);
            System.out.println("Paciente atualizado com sucesso!");
            System.out.println(paciente);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            new ExcecoesConexao(e);
        }
    }
}
