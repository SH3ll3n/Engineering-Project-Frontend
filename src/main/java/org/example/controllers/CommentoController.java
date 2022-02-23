package org.example.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.App;
import org.example.entities.Questionario;
import org.example.entities.Vacanza;
import org.example.utils.Request;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CommentoController {

    @FXML
    private TableColumn <Questionario,String> id;
    @FXML
    private TableColumn  <Questionario,String> ragazzo;
    @FXML
    private TableColumn commento;
    @FXML
    private TableView tabella;

    public void initialize() throws IOException {
        commento.setCellValueFactory(new PropertyValueFactory<>("commentoLibero"));
        id.setCellValueFactory(questionarioStringCellDataFeatures -> new SimpleStringProperty(
                String.valueOf(questionarioStringCellDataFeatures.getValue().getId().getIdVacanza()))
        );
        ragazzo.setCellValueFactory(questionarioStringCellDataFeatures -> new SimpleStringProperty(
                questionarioStringCellDataFeatures.getValue().getId().getEmailRagazzo()
        ));
        List<Questionario> questionari=getDati();
        questionari=questionari.stream().filter(questionario -> questionario!=null && questionario.getCommentoLibero()!=null).collect(Collectors.toList());
        questionari.forEach(questionario -> tabella.getItems().add(questionario));
    }

    public List<Questionario> getDati() throws IOException {
        StringBuilder dati= Request.get("/ragazzoVacanza/questionari");
        String jsonString=dati.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        //converti stringa in oggetto
        List<Questionario> questionari = objectMapper.readValue(jsonString, new TypeReference<>() {
        });
        return questionari;
    }

    public void torna(ActionEvent actionEvent) throws IOException {
        App.setRoot("ElencoVacanzeResponsabile");
    }
}
