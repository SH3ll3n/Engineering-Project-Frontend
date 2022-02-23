package org.example.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.App;
import org.example.entities.Vacanza;
import org.example.utils.Request;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ElencoVacResponsabileController {

    @FXML
    private TableView <Vacanza> tabella_id;
    @FXML
    private TableColumn data;
    @FXML
    private TableColumn durata;
    @FXML
    private TableColumn destinazione;
    @FXML
    private TableColumn lingua;
    @FXML
    private TableColumn id;

    public void initialize() throws IOException {
        StringBuilder stringBuilder=Request.get("vacanza");
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        List<Vacanza> vacanze=objectMapper.readValue(stringBuilder.toString(), new TypeReference<List<Vacanza>>() {});

       data.setCellValueFactory(new PropertyValueFactory<>("data"));
       durata.setCellValueFactory(new PropertyValueFactory<>("numeroSettimane"));
       destinazione.setCellValueFactory(new PropertyValueFactory<>("citta"));
       lingua.setCellValueFactory(new PropertyValueFactory<>("linguaStudiata"));
       id.setCellValueFactory(new PropertyValueFactory<>("id"));

        vacanze.forEach(vacanza -> tabella_id.getItems().add(vacanza));
    }

    public void tornaLogin(ActionEvent actionEvent) throws IOException {
        App.setRoot("Login",600,400);
    }

    public void vaiInsVacanza(ActionEvent actionEvent) throws IOException {
        App.setRoot("VacanzeResponsabile",707,480);
    }

    public void vaiQuestionari(ActionEvent actionEvent) throws IOException {
        App.setRoot("TabellaQuestionariResponsabile");
    }
}
