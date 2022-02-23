package org.example.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.App;
import org.example.entities.Gita;
import org.example.entities.GitaId;
import org.example.entities.Vacanza;
import org.example.utils.TrasferitoreDati;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

public class GitaController {

    @FXML
    public TableView <Gita>tabella1;
    @FXML
    public TableColumn <Gita, String>destinazioneGite;
    @FXML
    public TableColumn <Gita, Integer>costo;
    @FXML
    public TableColumn <Gita, Integer>numeroOre;
    @FXML
    public TableColumn <Gita, String>descrizione;

    @FXML
    public void initialize() {
        costo.setCellValueFactory(new PropertyValueFactory<>("costo"));
        numeroOre.setCellValueFactory(new PropertyValueFactory<>("numero_Ore"));
        descrizione.setCellValueFactory(new PropertyValueFactory<>("descrizione"));
        destinazioneGite.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getId().getDestinazione()));

        Set<Gita> dati = getDatiGita();

        //puntatore alla lista oggetti contenuti nella tabella
        dati.forEach(d -> tabella1.getItems().add(d));
    }

    public Set<Gita> getDatiGita() {
        TrasferitoreDati trasferitoreDati=TrasferitoreDati.getIstanza();
        return trasferitoreDati.getRagazzoVacanza().getVacanza().getGite();
    }

    public void passaAlloggio(ActionEvent actionEvent) throws IOException {
        App.setRoot("Alloggio");
    }
}
