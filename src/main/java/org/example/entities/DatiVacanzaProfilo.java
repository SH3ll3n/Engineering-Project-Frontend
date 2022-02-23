package org.example.entities;

import java.time.LocalDate;

public class DatiVacanzaProfilo {
    private String citta;
    private LocalDate data;
    private Integer numeroSettimane;
    private String linguaStudiata;
    private String alloggio;
    private String compagno;

    public RagazzoVacanzaId getRagazzoVacanzaId() {
        return ragazzoVacanzaId;
    }

    public void setRagazzoVacanzaId(RagazzoVacanzaId ragazzoVacanzaId) {
        this.ragazzoVacanzaId = ragazzoVacanzaId;
    }

    private RagazzoVacanzaId ragazzoVacanzaId;

    public DatiVacanzaProfilo() {
    }

    public DatiVacanzaProfilo(RagazzoVacanza ragazzoVacanza) {
        Vacanza vacanza= ragazzoVacanza.getVacanza();
        this.citta=vacanza.getCitta();
        this.data=vacanza.getData();
        this.numeroSettimane=vacanza.getNumeroSettimane();
        this.linguaStudiata=vacanza.getLinguaStudiata();
        this.ragazzoVacanzaId=ragazzoVacanza.getRagazzoVacanzaId();
        if (ragazzoVacanza.getCollege()!=null){
            this.alloggio="college";
        }else {
            this.alloggio="famiglia";
        }
        this.compagno= ragazzoVacanza.getNome_Amico();
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

    public String getAlloggio() {
        return alloggio;
    }

    public void setAlloggio(String alloggio) {
        this.alloggio = alloggio;
    }

    public String getCompagno() {
        return compagno;
    }

    public void setCompagno(String compagno) {
        this.compagno = compagno;
    }
}
