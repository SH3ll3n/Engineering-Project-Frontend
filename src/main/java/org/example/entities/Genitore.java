package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;


public class Genitore {

    private String email;

    private String nome;
    private String cognome;
    private String cellulare;

    public Genitore() {
    }

    private Set<Ragazzo> ragazzi = new HashSet<>();

    public Set<Ragazzo> getRagazzi() {
        return ragazzi;
    }

    public void setRagazzi(Set<Ragazzo> ragazzi) {
        this.ragazzi = ragazzi;
    }

    public Genitore(String email, String nome, String cognome, String cellulare, Set<Ragazzo> ragazzi) {
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.cellulare = cellulare;
        this.ragazzi = ragazzi;
    }

    public Genitore(String email, String nome, String cognome, String cellulare) {
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.cellulare = cellulare;
    }

    public Genitore(String nome, String cognome, String cellulare) {
        this.nome = nome;
        this.cognome = cognome;
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

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    @Override
    public String toString() {
        return "Genitore{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", cellulare=" + cellulare +
                '}';
    }
}
