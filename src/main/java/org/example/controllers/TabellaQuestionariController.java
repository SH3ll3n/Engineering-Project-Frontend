package org.example.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.App;
import org.example.entities.Questionario;
import org.example.entities.Ragazzo;
import org.example.utils.Request;
import org.example.utils.TrasferitoreDati;

import java.io.IOException;
import java.util.List;

public class TabellaQuestionariController {

    @FXML
    private TableColumn alloggio;
    @FXML
    private TableColumn citta;
    @FXML
    private TableColumn lingua;
    @FXML
    private TableColumn consiglieresti;
    @FXML
    private TableColumn vacanza;
    @FXML
    private TableColumn commento;
    @FXML
    private TableView tabella;

    public void initialize() throws IOException {
        alloggio.setCellValueFactory(new PropertyValueFactory<>("voto_alloggio"));
        citta.setCellValueFactory(new PropertyValueFactory<>("voto_citta"));
        lingua.setCellValueFactory(new PropertyValueFactory<>("voto_lingua"));
        consiglieresti.setCellValueFactory(new PropertyValueFactory<>("voto_consiglieresti"));
        vacanza.setCellValueFactory(new PropertyValueFactory<>("voto"));
        commento.setCellValueFactory(new PropertyValueFactory<>("commentoLibero"));

        ObjectMapper objectMapper=new ObjectMapper();
        TrasferitoreDati trasferitoreDati=TrasferitoreDati.getIstanza();
        Ragazzo ragazzo=trasferitoreDati.getRagazzo();
        StringBuilder testo=Request.get("ragazzoVacanza/questionari/"+ragazzo.getEmail());
        List<Questionario> questionarios=objectMapper.readValue(testo.toString(), new TypeReference<>(){});

        questionarios.forEach(questionario -> tabella.getItems().add(questionario));
    }

    public void vaiAProfilo(ActionEvent actionEvent) throws IOException {
        App.setRoot("Profilo");
    }
}
