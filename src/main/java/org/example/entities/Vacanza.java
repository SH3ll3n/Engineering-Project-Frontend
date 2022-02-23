package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Vacanza {

    private long id;
    private String citta;
    private LocalDate data;
    private Integer numeroSettimane;
    private String linguaStudiata;

    public Set<Gita> getGite() {
        return gite;
    }

    public void setGite(Set<Gita> gite) {
        this.gite = gite;
    }

    private Set<Gita> gite = new HashSet<>();

    public Vacanza() {
    }

    public Vacanza(Long id, String citta, LocalDate data, Integer numeroSettimane, String linguaStudiata) {
        this.id = id;
        this.citta = citta;
        this.data = data;
        this.numeroSettimane = numeroSettimane;
        this.linguaStudiata = linguaStudiata;
    }

    public Vacanza(String citta, LocalDate data, Integer numeroSettimane, String linguaStudiata) {
        this.citta = citta;
        this.data = data;
        this.numeroSettimane = numeroSettimane;
        this.linguaStudiata = linguaStudiata;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getNumeroSettimane() {
        return numeroSettimane;
    }

    public void setNumeroSettimane(Integer numeroSettimane) {
        this.numeroSettimane = numeroSettimane;
    }

    public String getLinguaStudiata() {
        return linguaStudiata;
    }

    public void setLinguaStudiata(String linguaStudiata) {
        this.linguaStudiata = linguaStudiata;
    }

    @Override
    public String toString() {
        return "Vacanza{" +
                "id=" + id +
                ", citta='" + citta + '\'' +
                ", data=" + data +
                ", numeroSettimane=" + numeroSettimane +
                ", linguaStudiata='" + linguaStudiata + '\'' +
                '}';
    }
}
