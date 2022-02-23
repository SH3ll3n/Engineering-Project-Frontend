package org.example.entities;

import java.util.HashSet;
import java.util.Set;

public class College {

    private String indirizzo;

    private String nome;

    private String nome_Attivita;
    private String descrizione_Attivita;

    public College() {
    }

    private Set<RagazzoVacanza> ragazziVacanza = new HashSet<>();

    public College(String indirizzo, String nome, String nome_Attivita, String descrizione_Attivita, Set<RagazzoVacanza> ragazziVacanza) {
        this.indirizzo = indirizzo;
        this.nome = nome;
        this.nome_Attivita = nome_Attivita;
        this.descrizione_Attivita = descrizione_Attivita;
        this.ragazziVacanza = ragazziVacanza;
    }

    public Set<RagazzoVacanza> getRagazziVacanza() {
        return ragazziVacanza;
    }

    public void setRagazziVacanza(Set<RagazzoVacanza> ragazziVacanza) {
        this.ragazziVacanza = ragazziVacanza;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome_Attivita() {
        return nome_Attivita;
    }

    public void setNome_Attivita(String nome_Attivita) {
        this.nome_Attivita = nome_Attivita;
    }

    public String getDescrizione_Attivita() {
        return descrizione_Attivita;
    }

    public void setDescrizione_Attivita(String descrizione_Attivita) {
        this.descrizione_Attivita = descrizione_Attivita;
    }
}
