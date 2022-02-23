package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.example.entities.RagazzoVacanza;
import org.example.entities.RagazzoVacanzaId;


public class Questionario {
    
    private RagazzoVacanzaId id = new RagazzoVacanzaId();
    private Integer voto;
    private String commentoLibero;
    private Integer voto_alloggio;
    private Integer voto_citta;
    private Integer voto_lingua;
    private Integer voto_consiglieresti;

    public Questionario(Integer voto, Integer voto_alloggio, Integer voto_citta, Integer voto_lingua, Integer voto_consiglieresti) {
        this.voto = voto;
        this.voto_alloggio = voto_alloggio;
        this.voto_citta = voto_citta;
        this.voto_lingua = voto_lingua;
        this.voto_consiglieresti = voto_consiglieresti;
    }

    public Questionario(RagazzoVacanzaId ragazzoVacanzaId, int votoVacanza, int voto_alloggio, int voto_citta, int voto_lingua, int voto_consiglieresti) {
        this.id=ragazzoVacanzaId;
        this.voto=votoVacanza;
        this.voto_alloggio = voto_alloggio;
        this.voto_citta = voto_citta;
        this.voto_lingua = voto_lingua;
        this.voto_consiglieresti = voto_consiglieresti;
    }

    public long getIdVacanza() {
        return id.getIdVacanza();
    }

    public void setIdVacanza(long idVacanza) {
        id.setIdVacanza(idVacanza);
    }

    public RagazzoVacanza getRagazzoVacanza() {
        return ragazzoVacanza;
    }

    public void setRagazzoVacanza(RagazzoVacanza ragazzoVacanza) {
        this.ragazzoVacanza = ragazzoVacanza;
    }

    public RagazzoVacanzaId getId() {
        return id;
    }

    public void setId(RagazzoVacanzaId id) {
        this.id = id;
    }
    
    private RagazzoVacanza ragazzoVacanza;

    public Questionario(Integer voto, String commentoLibero, Integer voto_alloggio, Integer voto_citta, Integer voto_lingua, Integer voto_consiglieresti) {
        this.voto = voto;
        this.commentoLibero = commentoLibero;
        this.voto_alloggio = voto_alloggio;
        this.voto_citta = voto_citta;
        this.voto_lingua = voto_lingua;
        this.voto_consiglieresti = voto_consiglieresti;
    }

    public Questionario() {
    }

    public Integer getVoto() {
        return voto;
    }

    public void setVoto(Integer voto) {
        this.voto = voto;
    }

    public String getCommentoLibero() {
        return commentoLibero;
    }

    public void setCommentoLibero(String commentoLibero) {
        this.commentoLibero = commentoLibero;
    }

    public Integer getVoto_alloggio() {
        return voto_alloggio;
    }

    public void setVoto_alloggio(Integer voto_alloggio) {
        this.voto_alloggio = voto_alloggio;
    }

    public Integer getVoto_citta() {
        return voto_citta;
    }

    public void setVoto_citta(Integer voto_citta) {
        this.voto_citta = voto_citta;
    }

    public Integer getVoto_lingua() {
        return voto_lingua;
    }

    public void setVoto_lingua(Integer voto_lingua) {
        this.voto_lingua = voto_lingua;
    }

    public Integer getVoto_consiglieresti() {
        return voto_consiglieresti;
    }

    public void setVoto_consiglieresti(Integer voto_consiglieresti) {
        this.voto_consiglieresti = voto_consiglieresti;
    }
}
