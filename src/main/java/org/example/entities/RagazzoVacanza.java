package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RagazzoVacanza {

    private RagazzoVacanzaId ragazzoVacanzaId = new RagazzoVacanzaId();

    public RagazzoVacanza(){
    }

    private Tipo tipo_Camera;

    public Tipo getTipo_Camera() {
        return tipo_Camera;
    }

    public void setTipo_Camera(Tipo tipo_Camera) {
        this.tipo_Camera = tipo_Camera;
    }

    public String getNome_Amico() {
        return nome_Amico;
    }

    public void setNome_Amico(String nome_Amico) {
        this.nome_Amico = nome_Amico;
    }

    public String getEmail_Amico() {
        return email_Amico;
    }

    public void setEmail_Amico(String email_Amico) {
        this.email_Amico = email_Amico;
    }

    private String nome_Amico;
    private String email_Amico;

    private Livello livello;

    private Pagamento pagamento;

    private Ragazzo ragazzo;

    private Vacanza vacanza;

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }

    private Questionario questionario;

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public Famiglia getFamiglia() {
        return famiglia;
    }

    public void setFamiglia(Famiglia famiglia) {
        this.famiglia = famiglia;
    }

    //relazione college
    private College college;

    //relazione famiglia
    private Famiglia famiglia;


    public RagazzoVacanza(RagazzoVacanzaId ragazzoVacanzaId, Livello livello, Pagamento pagamento, Ragazzo ragazzo, Vacanza vacanza) {
        this.ragazzoVacanzaId = ragazzoVacanzaId;
        this.livello = livello;
        this.pagamento = pagamento;
        this.ragazzo = ragazzo;
        this.vacanza = vacanza;
    }

    public RagazzoVacanzaId getRagazzoVacanzaId() {
        return ragazzoVacanzaId;
    }

    public void setRagazzoVacanzaId(RagazzoVacanzaId ragazzoVacanzaId) {
        this.ragazzoVacanzaId = ragazzoVacanzaId;
    }

    public Livello getLivello() {
        return livello;
    }

    public void setLivello(Livello livello) {
        this.livello = livello;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    @JsonIgnore
    public Ragazzo getRagazzo() {
        return ragazzo;
    }

    public void setRagazzo(Ragazzo ragazzo) {
        this.ragazzo = ragazzo;
    }

    public Vacanza getVacanza() {
        return vacanza;
    }

    public void setVacanza(Vacanza vacanza) {
        this.vacanza = vacanza;
    }
}
