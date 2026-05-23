package br.com.fiap.main;

import br.com.fiap.bo.DentistaBO;
import br.com.fiap.entities.Dentista;
import br.com.fiap.excecoes.ExcecoesConexao;

public class TesteInserirDentista {

    public static void main(String[] args) {
        try {
            DentistaBO dentistaBO = new DentistaBO();

            Dentista dentista = new Dentista();
            dentista.setNome("Dra. Fernanda Rocha");
            dentista.setCro("CRO-SP 98765");
            dentista.setEspecialidade("Clínico Geral");
            dentista.setEmail("fernanda@turmadobem.org");
            dentista.setCidade("São Paulo");

            dentistaBO.inserirBo(dentista);
            System.out.println("Dentista inserido com sucesso!");
            System.out.println(dentista);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            new ExcecoesConexao(e);
        }
    }
}
