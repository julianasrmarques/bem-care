package br.com.fiap.main;

import br.com.fiap.bo.DentistaBO;
import br.com.fiap.entities.Dentista;
import br.com.fiap.excecoes.ExcecoesConexao;

import java.util.ArrayList;

public class TesteSelecionarDentista {

    public static void main(String[] args) {
        try {
            DentistaBO dentistaBO = new DentistaBO();

            ArrayList<Dentista> lista = dentistaBO.selecionarBo();
            System.out.println("Total de dentistas encontrados: " + lista.size());
            System.out.println("-------------------------------");
            for (Dentista d : lista) {
                System.out.println(d);
            }

        } catch (Exception e) {
            new ExcecoesConexao(e);
        }
    }
}
