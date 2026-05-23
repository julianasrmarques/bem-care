package br.com.fiap.main;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.excecoes.ExcecoesConexao;

import java.sql.Connection;

public class TesteConexao {

    public static void main(String[] args) {
        try {
            Connection conexao = new ConexaoFactory().conexao();
            System.out.println("Conexão estabelecida com sucesso!");
            System.out.println("Banco: " + conexao.getMetaData().getDatabaseProductName());
            conexao.close();
        } catch (Exception e) {
            new ExcecoesConexao(e);
        }
    }
}
