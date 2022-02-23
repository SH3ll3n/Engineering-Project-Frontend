package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.App;
import org.example.entities.Questionario;
import org.example.entities.Ragazzo;
import org.example.entities.RagazzoVacanza;
import org.example.entities.Vacanza;
import org.example.utils.Request;
import org.example.utils.TrasferitoreDati;

import java.io.IOException;
import java.io.PipedReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class CompilazioneQuestionarioController {

    @FXML
    private Slider alloggio_slider;
    @FXML
    private Slider citta_slider;
    @FXML
    private  Slider lingua_slider;
    @FXML
    private Slider consiglio_slider;
    @FXML
    private Slider vacanza_slider;
    @FXML
    private TextArea commento_text;
    @FXML
    private Label nomeVacanza_label;
    @FXML
    private Button tab_button;
    @FXML
    private Button indietro_button;
    @FXML
    private Button conferma_button;
    @FXML
    private Label striscia;

    private RagazzoVacanza ragazzoVacanza;

    @FXML
    public void initialize() throws IOException {
        TrasferitoreDati trasferitoreDati=TrasferitoreDati.getIstanza();
        Ragazzo ragazzo= trasferitoreDati.getRagazzo();
        Set<RagazzoVacanza> ragazziVacanze= ragazzo.getRagazziVacanze();
        boolean flag=true;
        for (RagazzoVacanza rv:ragazziVacanze) {
            Vacanza vacanza = rv.getVacanza();
            LocalDate dataFine = vacanza.getData().plusWeeks(
                    vacanza.getNumeroSettimane()
            );
            if(rv.getQuestionario()==null && dataFine.isBefore(LocalDate.now())){
                flag=false;
                nomeVacanza_label.setText(rv.getVacanza().getCitta()+" del "+rv.getVacanza().getData());
                this.ragazzoVacanza= rv;
                break;
            }
        }
        if(flag){
            conferma_button.setVisible(false);
            tab_button.setVisible(true);
            alloggio_slider.setDisable(true);
            citta_slider.setDisable(true);
            lingua_slider.setDisable(true);
            consiglio_slider.setDisable(true);
            commento_text.setDisable(true);
            vacanza_slider.setDisable(true);
            striscia.setVisible(true);
        }
    }

    public void tornaProfilo(ActionEvent actionEvent) throws IOException {
        App.setRoot("Profilo", 600, 420);
    }

    public void vaiTabellaFinale(ActionEvent actionEvent) throws IOException {
        Integer alloggio= (int) alloggio_slider.getValue();
        Integer citta= (int) citta_slider.getValue();
        Integer lingua= (int) lingua_slider.getValue();
        Integer consiglio= (int) consiglio_slider.getValue();
        Integer vacanza= (int) vacanza_slider.getValue();
        String commento= commento_text.getText();


        Questionario questionario=new Questionario(vacanza,commento,alloggio,citta,lingua,consiglio);
        ragazzoVacanza.setQuestionario(questionario);
        Request.put("/ragazzoVacanza/update", ragazzoVacanza);
        App.setRoot("TabellaQuestionari", 600,420);
    }

    public void vai(ActionEvent actionEvent) throws IOException {
        App.setRoot("TabellaQuestionari", 600,420);
    }
}
