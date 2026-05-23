package br.com.fiap.entities;

public class Agendamento {

    private int id;
    private String nomePaciente;
    private String responsavel;
    private String telefone;
    private String cidade;
    private String dataPreferida;
    private String periodo;    // "Manhã" | "Tarde" | "Noite"
    private String motivo;
    private String status;     // "Pendente" | "Confirmado" | "Recusado"
    private String criadoEm;

    public Agendamento() {
        super();
    }

    public Agendamento(int id, String nomePaciente, String responsavel, String telefone,
                       String cidade, String dataPreferida, String periodo,
                       String motivo, String status, String criadoEm) {
        super();
        this.id = id;
        this.nomePaciente = nomePaciente;
        this.responsavel = responsavel;
        this.telefone = telefone;
        this.cidade = cidade;
        this.dataPreferida = dataPreferida;
        this.periodo = periodo;
        this.motivo = motivo;
        this.status = status;
        this.criadoEm = criadoEm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getDataPreferida() {
        return dataPreferida;
    }

    public void setDataPreferida(String dataPreferida) {
        this.dataPreferida = dataPreferida;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(String criadoEm) {
        this.criadoEm = criadoEm;
    }

    @Override
    public String toString() {
        return "Agendamento{" +
                "id=" + id +
                ", nomePaciente='" + nomePaciente + '\'' +
                ", telefone='" + telefone + '\'' +
                ", dataPreferida='" + dataPreferida + '\'' +
                ", periodo='" + periodo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
