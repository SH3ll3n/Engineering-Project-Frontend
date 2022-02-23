package org.example.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.example.App;
import org.example.entities.Questionario;
import org.example.entities.Ragazzo;
import org.example.entities.RagazzoVacanzaId;
import org.example.entities.Vacanza;
import org.example.utils.Request;
import org.example.utils.TrasferitoreDati;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TabellaQuestionariResponsabileController {

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
    private TableColumn <Questionario,String> id;
    @FXML
    private TableView tabella;

    public void initialize() throws IOException {

        alloggio.setCellValueFactory(new PropertyValueFactory<>("voto_alloggio"));
        citta.setCellValueFactory(new PropertyValueFactory<>("voto_citta"));
        lingua.setCellValueFactory(new PropertyValueFactory<>("voto_lingua"));
        consiglieresti.setCellValueFactory(new PropertyValueFactory<>("voto_consiglieresti"));
        vacanza.setCellValueFactory(new PropertyValueFactory<>("voto"));

        id.setCellValueFactory(questionarioStringCellDataFeatures -> new SimpleStringProperty(
                String.valueOf(questionarioStringCellDataFeatures.getValue().getId().getIdVacanza()))
        );


        List<Vacanza> vacanze=getDati();
        ObjectMapper objectMapper=new ObjectMapper();
        vacanze.forEach(vacanza1 -> {
            StringBuilder testo= null;
            try {
                testo = Request.get("ragazzoVacanza/questionari/vacanza/"+vacanza1.getId());
                List<Questionario> questionarios=objectMapper.readValue(testo.toString(), new TypeReference<>(){});
                List<Integer> votiVacanza=new ArrayList<>();
                List<Integer> votiAlloggio=new ArrayList<>();
                List<Integer> votiCitta=new ArrayList<>();
                List<Integer> votiConsiglieresti=new ArrayList<>();
                List<Integer> votiLingua=new ArrayList<>();
                questionarios.removeAll(Collections.singleton(null));
                for (Questionario questionario : questionarios) {
                    votiVacanza.add(questionario.getVoto());
                    votiAlloggio.add(questionario.getVoto_alloggio());
                    votiCitta.add(questionario.getVoto_citta());
                    votiConsiglieresti.add(questionario.getVoto_consiglieresti());
                    votiLingua.add(questionario.getVoto_lingua());
                }
               if(! questionarios.isEmpty()){
                   Double mediaVacanza=votiVacanza.stream().mapToDouble(d->d).average().orElse(0.0);
                   Double mediaAlloggio=votiAlloggio.stream().mapToDouble(d->d).average().orElse(0.0);
                   Double mediaCitta=votiCitta.stream().mapToDouble(d->d).average().orElse(0.0);
                   Double mediaConsiglieresti=votiConsiglieresti.stream().mapToDouble(d->d).average().orElse(0.0);
                   Double mediaLingua=votiLingua.stream().mapToDouble(d->d).average().orElse(0.0);

                   tabella.getItems().add(
                           new Questionario(
                                   new RagazzoVacanzaId(vacanza1.getId()),
                                   mediaVacanza.intValue(),
                                   mediaAlloggio.intValue(),
                                   mediaCitta.intValue(),
                                   mediaLingua.intValue(),
                                   mediaConsiglieresti.intValue()
                           )
                   );
               }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public List<Vacanza> getDati() throws IOException {
        StringBuilder dati= Request.get("/vacanza");
        String jsonString=dati.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        //converti stringa in oggetto
        List<Vacanza> vacanze = objectMapper.readValue(jsonString, new TypeReference<List<Vacanza>>() {
        });
        return vacanze;
    }

    public void compilazioneQuestionario(ActionEvent actionEvent) {
    }

    public void vaiCommenti(ActionEvent actionEvent) throws IOException {
        App.setRoot("Commento");
    }
}
