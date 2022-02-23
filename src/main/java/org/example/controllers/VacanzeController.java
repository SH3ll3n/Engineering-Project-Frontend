package org.example.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.App;
import org.example.entities.Ragazzo;
import org.example.entities.RagazzoVacanza;
import org.example.entities.Vacanza;
import org.example.utils.Request;
import org.example.utils.TrasferitoreDati;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VacanzeController {

    @FXML
    public TableView <Vacanza> tabella_id;
    @FXML
    public TableColumn data;
    @FXML
    public TableColumn durata;
    @FXML
    public TableColumn destinazione;
    @FXML
    public TableColumn lingua;
    @FXML
    public TableColumn id;

    @FXML
    public void initialize() throws IOException {

        TrasferitoreDati trasferitoreDati= TrasferitoreDati.getIstanza();
        Ragazzo ragazzo=trasferitoreDati.getRagazzo();

        data.setCellValueFactory(new PropertyValueFactory<>("data"));
        durata.setCellValueFactory(new PropertyValueFactory<>("numeroSettimane"));
        destinazione.setCellValueFactory(new PropertyValueFactory<>("citta"));
        lingua.setCellValueFactory(new PropertyValueFactory<>("linguaStudiata"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        List<Vacanza> dati= getDati();

        List<Vacanza> finalDati = dati;
        tabella_id.setOnMousePressed(new EventHandler<>()  {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    try {
                        //metto selezione perché non c è il doppio click
                        Vacanza vacanza=tabella_id.getSelectionModel().getSelectedItem();
                        Long id_vacanza= vacanza.getId();
                        //filter prende una collezione di oggetti e la filtra mediante una lambda che vuole un valore booleano dopo la freccia
                        //confronto id backend e frontend
                        Optional<Vacanza> vacanzaOptional= finalDati.stream().filter(vacanza1 -> vacanza1.getId().equals(id_vacanza)).findFirst();
                        RagazzoVacanza ragazzoVacanza=new RagazzoVacanza();
                        //aggiunge a ragazzovacanza una vacanza, optional è oggetto che contiene un altro oggetto
                        ragazzoVacanza.setVacanza(vacanzaOptional.get());
                        trasferitoreDati.setRagazzoVacanza(ragazzoVacanza);
                        App.setRoot("Gita");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        if (!dati.isEmpty()) {
            List<Long> ids=ragazzo.getRagazziVacanze().stream().map(ragazzoVacanza -> ragazzoVacanza.getVacanza().getId()).collect(Collectors.toList());
            dati=dati.stream().filter(vacanza -> !ids.contains(vacanza.getId())).collect(Collectors.toList());
            dati.forEach(vacanza -> tabella_id.getItems().add(vacanza));
        }
    }

    public List<Vacanza> getDati() throws IOException {
        StringBuilder dati=Request.get("/vacanza");
        String jsonString=dati.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        //converti stringa in oggetto
        List<Vacanza> vacanze = objectMapper.readValue(jsonString, new TypeReference<List<Vacanza>>() {
        });
        return vacanze;
    }

    @FXML
    private void tornaLogin() throws IOException {
        App.setRoot("Profilo");
    }
}
