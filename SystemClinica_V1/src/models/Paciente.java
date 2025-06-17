package models;

public class Paciente {

    private String nome;
    private int idade;
    private String prontuario;
    private boolean atendido;  // Status se o paciente foi atendido
    private int sala;          // Sala atribuída ao paciente

    public Paciente(String nome, int idade, String prontuario) {
        this.nome = nome;
        this.idade = idade;
        this.prontuario = prontuario;
        this.atendido = false; // Por padrão, paciente não foi atendido
        this.sala = -1; // Sala não atribuída inicialmente
    }

    // Getters e Setters
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

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public boolean isAtendido() {
        return atendido;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }

    @Override
    public String toString() {
        String status = atendido ? "Atendido" : "Ainda não foi atendido";
        return nome + " - Prontuário: " + prontuario + " - " + status + " - Sala: " + sala;
    }
}
