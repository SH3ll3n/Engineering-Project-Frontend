package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Allergia {
    private AllergiaId id;

    private String precauzione;

    public Allergia(AllergiaId id, String precauzione, Ragazzo ragazzo) {
        this.id = id;
        this.precauzione = precauzione;
        this.ragazzo= ragazzo;
    }

    public Ragazzo getRagazzo() {
        return ragazzo;
    }

    public void setRagazzo(Ragazzo ragazzo) {
        this.ragazzo = ragazzo;
    }

    private Ragazzo ragazzo;

    public Allergia(AllergiaId id, String precauzione) {
        this.id = id;
        this.precauzione = precauzione;
    }

    public Allergia(String precauzione) {
        this.precauzione = precauzione;
    }

    public Allergia() {
    }

    public AllergiaId getId() {
        return id;
    }

    public void setId(AllergiaId id) {
        this.id = id;
    }

    public String getPrecauzione() {
        return precauzione;
    }

    public void setPrecauzione(String precauzione) {
        this.precauzione = precauzione;
    }

    @Override
    public String toString() {
        return "Allergia{" +
                "id=" + id +
                ", precauzione='" + precauzione + '\'' +
                '}';
    }
}
