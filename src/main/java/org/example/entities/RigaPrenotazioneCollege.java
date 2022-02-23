package org.example.entities;

public class RigaPrenotazioneCollege {
    private String nomeCollege;
    private Tipo tipoStanza;
    private String compagno;


    public RigaPrenotazioneCollege(String nomeCollege, Tipo tipoStanza, String compagno) {
        this.nomeCollege = nomeCollege;
        this.tipoStanza = tipoStanza;
        this.compagno = compagno;
    }

    public RigaPrenotazioneCollege(String nomeCollege, Tipo tipoStanza) {
        this.nomeCollege = nomeCollege;
        this.tipoStanza = tipoStanza;
    }

    public String getNomeCollege() {
        return nomeCollege;
    }

    public void setNomeCollege(String nomeCollege) {
        this.nomeCollege = nomeCollege;
    }

    public Tipo getTipoStanza() {
        return tipoStanza;
    }

    public void setTipoStanza(Tipo tipoStanza) {
        this.tipoStanza = tipoStanza;
    }

    public String getCompagno() {
        return compagno;
    }

    public void setCompagno(String compagno) {
        this.compagno = compagno;
    }
}
