package org.example.entities;

import java.io.Serializable;

public class RagazzoVacanzaId implements Serializable {

    private String emailRagazzo;
    private long idVacanza;

    public RagazzoVacanzaId(long idVacanza) {
        this.idVacanza = idVacanza;
    }

    public String getEmailRagazzo() {
        return emailRagazzo;
    }

    public void setEmailRagazzo(String emailRagazzo) {
        this.emailRagazzo = emailRagazzo;
    }

    public long getIdVacanza() {
        return idVacanza;
    }

    public void setIdVacanza(long idVacanza) {
        this.idVacanza = idVacanza;
    }

    public RagazzoVacanzaId() {
    }

    public RagazzoVacanzaId(String emailRagazzo, long idVacanza) {
        this.emailRagazzo = emailRagazzo;
        this.idVacanza = idVacanza;
    }
}
