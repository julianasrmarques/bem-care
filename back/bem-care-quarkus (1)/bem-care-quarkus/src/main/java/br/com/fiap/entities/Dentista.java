package br.com.fiap.entities;

public class Dentista {

    private int id;
    private String nome;
    private String cro;
    private String especialidade;
    private String email;
    private String cidade;

    public Dentista() {
        super();
    }

    public Dentista(int id, String nome, String cro, String especialidade, String email, String cidade) {
        super();
        this.id = id;
        this.nome = nome;
        this.cro = cro;
        this.especialidade = especialidade;
        this.email = email;
        this.cidade = cidade;
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

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Dentista{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cro='" + cro + '\'' +
                ", especialidade='" + especialidade + '\'' +
                ", email='" + email + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }
}
