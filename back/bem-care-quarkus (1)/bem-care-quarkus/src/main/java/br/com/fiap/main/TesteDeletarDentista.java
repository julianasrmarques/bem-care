package br.com.fiap.main;

import br.com.fiap.bo.DentistaBO;
import br.com.fiap.excecoes.ExcecoesConexao;

public class TesteDeletarDentista {

    public static void main(String[] args) {
        try {
            DentistaBO dentistaBO = new DentistaBO();

            // Altere o ID para o dentista que deseja deletar
            int idParaDeletar = 1;

            dentistaBO.deletarBo(idParaDeletar);
            System.out.println("Dentista com ID " + idParaDeletar + " deletado com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            new ExcecoesConexao(e);
        }
    }
}
