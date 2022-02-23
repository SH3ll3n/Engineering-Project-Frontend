package org.example.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import org.example.App;
import org.example.entities.DatiVacanzaProfilo;
import org.example.entities.Gita;
import org.example.entities.Vacanza;
import org.example.utils.Request;
import org.example.utils.TrasferitoreDati;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class VacanzaResponsabileController {

    @FXML
    private TableView <Gita> tabella1;
    @FXML
    private TableColumn <Gita,String> destinazioneGite;
    @FXML
    private TableColumn costo;
    @FXML
    private TableColumn numeroOre;
    @FXML
    private TableColumn descrizione;
    @FXML
    private TextField durata;
    @FXML
    private DatePicker dataPartenza;
    @FXML
    private TextField destinazione;
    @FXML
    private TextField linguaStudiata;
    @FXML
    private Label errore;

    public void tornaLista() throws IOException {
        App.setRoot("ElencoVacanzeResponsabile", 650, 450);
    }

    public void vaiInserimentoGita(ActionEvent actionEvent) throws IOException {
        App.setRoot("InserimentoGite", 590, 400);
    }

    //funzione che modifica DatePicker(calendario) usando dal formato americano a quello europeo
    private void setDatePickerDateFormat() {
        dataPartenza.setConverter(new StringConverter<LocalDate>() {
            private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate localDate) {
                if (localDate == null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString) {
                if (dateString == null || dateString.trim().isEmpty()) {
                    return null;
                }
                return LocalDate.parse(dateString, dateTimeFormatter);
            }
        });
    }

    public void initialize(){
        setDatePickerDateFormat();

        destinazioneGite.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getId().getDestinazione()));
        costo.setCellValueFactory(new PropertyValueFactory<>("costo"));
        numeroOre.setCellValueFactory(new PropertyValueFactory<>("numero_Ore"));
        descrizione.setCellValueFactory(new PropertyValueFactory<>("descrizione"));
        TrasferitoreDati trasferitoreDati=TrasferitoreDati.getIstanza();
        List<Gita> listaGite= trasferitoreDati.getGite();
        listaGite.forEach(gita -> tabella1.getItems().add(gita));
    }

    public void confermaAction(ActionEvent actionEvent) throws IOException {
        if (!tabella1.getItems().isEmpty() && !durata.getText().isEmpty() && !destinazione.getText().isEmpty() &&
                !linguaStudiata.getText().isEmpty() && dataPartenza != null) {
            TrasferitoreDati trasferitoreDati=TrasferitoreDati.getIstanza();
            List<Gita> gite=trasferitoreDati.getGite();
            String durataText=durata.getText();
            String destinazioneText=destinazione.getText();
            LocalDate dataPartenzaText=dataPartenza.getValue();
            String linguaStudiataText=linguaStudiata.getText();
            Vacanza vacanza=new Vacanza(
                    destinazioneText,
                    dataPartenzaText,
                    Integer.parseInt(durataText),
                    linguaStudiataText
            );
            vacanza.setGite(new HashSet<>(gite));
            Request.post("vacanza", vacanza);
            trasferitoreDati.setGite(new ArrayList<>());
            tornaLista();
        } else {
            errore.setVisible(true);
        }
    }
}

