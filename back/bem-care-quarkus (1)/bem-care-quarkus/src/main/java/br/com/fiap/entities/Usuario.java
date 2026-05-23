package br.com.fiap.entities;

public class Usuario {

    private int id;
    private String email;
    private String senha;
    private String perfil;   // "ADMIN" | "VOLUNTARIO"

    public Usuario() {
        super();
    }

    public Usuario(int id, String email, String senha, String perfil) {
        super();
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", perfil='" + perfil + '\'' +
                '}';
    }
}
