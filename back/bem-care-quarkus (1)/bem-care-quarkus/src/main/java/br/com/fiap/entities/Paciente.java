package br.com.fiap.entities;

public class Paciente {

    private int id;
    private String nome;
    private int idade;
    private String responsavel;
    private String cidade;
    private String telefone;

    public Paciente() {
        super();
    }

    public Paciente(int id, String nome, int idade, String responsavel, String cidade, String telefone) {
        super();
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.responsavel = responsavel;
        this.cidade = cidade;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", responsavel='" + responsavel + '\'' +
                ", cidade='" + cidade + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
