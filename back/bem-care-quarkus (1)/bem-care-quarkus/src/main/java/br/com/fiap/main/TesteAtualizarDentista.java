package br.com.fiap.main;

import br.com.fiap.bo.DentistaBO;
import br.com.fiap.entities.Dentista;
import br.com.fiap.excecoes.ExcecoesConexao;

public class TesteAtualizarDentista {

    public static void main(String[] args) {
        try {
            DentistaBO dentistaBO = new DentistaBO();

            // Altere o ID para um existente no banco
            Dentista dentista = new Dentista();
            dentista.setId(1);
            dentista.setNome("Dra. Fernanda Rocha Atualizada");
            dentista.setCro("CRO-SP 98765");
            dentista.setEspecialidade("Ortodontia");
            dentista.setEmail("fernanda.novo@turmadobem.org");
            dentista.setCidade("Campinas");

            dentistaBO.atualizarBo(dentista);
            System.out.println("Dentista atualizado com sucesso!");
            System.out.println(dentista);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            new ExcecoesConexao(e);
        }
    }
}
