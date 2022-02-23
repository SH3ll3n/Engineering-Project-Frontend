package org.example.entities;

import java.util.HashSet;
import java.util.Set;

public class Famiglia {

        private String nome_Capo;

        private String cognome_Capo;
        private Integer componenti;
        private Integer animali;
        private Integer numero_Camere;
        private Integer numero_Bagni;
        private Integer distanza_Centro;

    public Famiglia() {
    }

    private Set<RagazzoVacanza> ragazziVacanza = new HashSet<>();

    public Set<RagazzoVacanza> getRagazziVacanza() {
        return ragazziVacanza;
    }

    public void setRagazziVacanza(Set<RagazzoVacanza> ragazziVacanza) {
        this.ragazziVacanza = ragazziVacanza;
    }

    public Famiglia(String nome_Capo, String cognome_Capo, Integer componenti, Integer animali, Integer numero_Camere, Integer numero_Bagni, Integer distanza_Centro, Set<RagazzoVacanza> ragazziVacanza) {
        this.nome_Capo = nome_Capo;
        this.cognome_Capo = cognome_Capo;
        this.componenti = componenti;
        this.animali = animali;
        this.numero_Camere = numero_Camere;
        this.numero_Bagni = numero_Bagni;
        this.distanza_Centro = distanza_Centro;
        this.ragazziVacanza = ragazziVacanza;
    }

    public Famiglia(String nome_Capo, String cognome_Capo, Integer componenti, Integer animali, Integer numero_Camere, Integer numero_Bagni, Integer distanza_Centro) {
        this.nome_Capo = nome_Capo;
        this.cognome_Capo = cognome_Capo;
        this.componenti = componenti;
        this.animali = animali;
        this.numero_Camere = numero_Camere;
        this.numero_Bagni = numero_Bagni;
        this.distanza_Centro = distanza_Centro;
    }

    public Famiglia(Integer componenti, Integer animali, Integer numero_Camere, Integer numero_Bagni, Integer distanza_Centro) {
        this.componenti = componenti;
        this.animali = animali;
        this.numero_Camere = numero_Camere;
        this.numero_Bagni = numero_Bagni;
        this.distanza_Centro = distanza_Centro;
    }

    public String getNome_Capo() {
        return nome_Capo;
    }

    public void setNome_Capo(String nome_Capo) {
        this.nome_Capo = nome_Capo;
    }

    public String getCognome_Capo() {
        return cognome_Capo;
    }

    public void setCognome_Capo(String cognome_Capo) {
        this.cognome_Capo = cognome_Capo;
    }

    public Integer getComponenti() {
        return componenti;
    }

    public void setComponenti(Integer componenti) {
        this.componenti = componenti;
    }

    public Integer getAnimali() {
        return animali;
    }

    public void setAnimali(Integer animali) {
        this.animali = animali;
    }

    public Integer getNumero_Camere() {
        return numero_Camere;
    }

    public void setNumero_Camere(Integer numero_Camere) {
        this.numero_Camere = numero_Camere;
    }

    public Integer getNumero_Bagni() {
        return numero_Bagni;
    }

    public void setNumero_Bagni(Integer numero_Bagni) {
        this.numero_Bagni = numero_Bagni;
    }

    public Integer getDistanza_Centro() {
        return distanza_Centro;
    }

    public void setDistanza_Centro(Integer distanza_Centro) {
        this.distanza_Centro = distanza_Centro;
    }
}
