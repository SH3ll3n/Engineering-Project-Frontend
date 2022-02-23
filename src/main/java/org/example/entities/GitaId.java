package org.example.entities;

import java.io.Serializable;

public class GitaId implements Serializable {
    private String destinazione;

    public GitaId(){
    }

    public long getIdVacanza() {
        return idVacanza;
    }

    public void setIdVacanza(long idVacanza) {
        this.idVacanza = idVacanza;
    }

    public GitaId(String destinazione, long idVacanza) {
        this.destinazione = destinazione;
        this.idVacanza = idVacanza;
    }

    private long idVacanza;

    public GitaId(String destinazione) {
        this.destinazione = destinazione;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    @Override
    public String toString() {
        return "GitaId{" +
                "destinazione='" + destinazione + '\'' +
                ", idVacanza=" + idVacanza +
                '}';
    }
}
