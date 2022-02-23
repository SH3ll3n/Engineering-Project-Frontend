package org.example.utils;

import org.example.entities.*;

import java.util.ArrayList;
import java.util.List;

//singleton, ogni classe ha lo stesso oggetto, trasferire dati tra interfacce frontend
public class TrasferitoreDati {

    private static final TrasferitoreDati istanza= new TrasferitoreDati();

    public Ragazzo getRagazzo() {
        return ragazzo;
    }

    public void setRagazzo(Ragazzo ragazzo) {
        this.ragazzo = ragazzo;
    }

    private Ragazzo ragazzo;

    public Vacanza getVacanzaQuestionario() {
        return vacanzaQuestionario;
    }

    public void setVacanzaQuestionario(Vacanza vacanzaQuestionario) {
        this.vacanzaQuestionario = vacanzaQuestionario;
    }

    private Vacanza vacanzaQuestionario;
    private Famiglia famiglia;

    public List<Gita> getGite() {
        return gite;
    }

    public void setGite(List<Gita> gite) {
        this.gite = gite;
    }

    private List<Gita> gite= new ArrayList<>();

    public RagazzoVacanza getRagazzoVacanza() {
        return ragazzoVacanza;
    }

    public void setRagazzoVacanza(RagazzoVacanza ragazzoVacanza) {
        this.ragazzoVacanza = ragazzoVacanza;
    }

    private RagazzoVacanza ragazzoVacanza;

    public Famiglia getFamiglia() {
        return famiglia;
    }

    public void setFamiglia(Famiglia famiglia) {
        this.famiglia = famiglia;
    }

    private TrasferitoreDati() {
    }

    public static TrasferitoreDati getIstanza() {
        return istanza;
    }

}
