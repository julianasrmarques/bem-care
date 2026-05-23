package br.com.fiap.main;

import br.com.fiap.bo.PacienteBO;
import br.com.fiap.excecoes.ExcecoesConexao;

public class TesteDeletarPaciente {

    public static void main(String[] args) {
        try {
            PacienteBO pacienteBO = new PacienteBO();

            // Altere o ID para o paciente que deseja deletar
            int idParaDeletar = 1;

            pacienteBO.deletarBo(idParaDeletar);
            System.out.println("Paciente com ID " + idParaDeletar + " deletado com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            new ExcecoesConexao(e);
        }
    }
}
