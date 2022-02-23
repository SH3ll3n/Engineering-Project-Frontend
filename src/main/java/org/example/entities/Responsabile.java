package org.example.entities;

public class Responsabile {

    private String email;

    private String nome;
    private String cognome;
    private String password;
    private Integer cellulare;

    public Responsabile() {
    }

    public Responsabile(String email, String nome, String cognome, String password, Integer cellulare) {
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        this.cellulare = cellulare;
    }

    public Responsabile(String nome, String cognome, String password, Integer cellulare) {
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        this.cellulare = cellulare;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCellulare() {
        return cellulare;
    }

    public void setCellulare(Integer cellulare) {
        this.cellulare = cellulare;
    }

    @Override
    public String toString() {
        return "Responsabile{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", password='" + password + '\'' +
                ", cellulare=" + cellulare +
                '}';
    }
}
