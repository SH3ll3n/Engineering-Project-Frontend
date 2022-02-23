package org.example.entities;

public class Gita {

    public GitaId getId() {
        return id;
    }

    public void setId(GitaId id) {
        this.id = id;
    }

    private GitaId id;

    private String descrizione;
    private Integer costo;
    private Integer numero_Ore;

    public Gita() {
    }

    public Gita(GitaId id, String descrizione, Integer costo, Integer numero_Ore) {
        this.id = id;
        this.descrizione = descrizione;
        this.costo = costo;
        this.numero_Ore = numero_Ore;
    }

    public Gita(String descrizione, Integer costo, Integer numero_Ore) {
        this.descrizione = descrizione;
        this.costo = costo;
        this.numero_Ore = numero_Ore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public Integer getNumero_Ore() {
        return numero_Ore;
    }

    public void setNumero_Ore(Integer numero_Ore) {
        this.numero_Ore = numero_Ore;
    }

    @Override
    public String toString() {
        return "Gita{" +
                "id='" + id + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", costo=" + costo +
                ", numero_Ore=" + numero_Ore +
                '}';
    }
}




