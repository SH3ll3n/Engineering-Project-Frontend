package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.entities.Genitore;
import org.example.entities.Ragazzo;
import org.example.utils.Request;
import org.example.utils.TrasferitoreDati;

import java.io.IOException;

public class DatiGenitoriController {

    @FXML
    TextField nomeGenitore1_field;
    @FXML
    TextField cognomeGenitore1_field;
    @FXML
    TextField emailGenitore1_field;
    @FXML
    TextField telefonoGenitore1_field;
    @FXML
    TextField nomeGenitore2_field;
    @FXML
    TextField cognomeGenitore2_field;
    @FXML
    TextField emailGenitore2_field;
    @FXML
    TextField telefonoGenitore2_field;
    @FXML
    Label errore_label;

    @FXML
    private void tornaIscrizione() throws IOException {
        App.setRoot("IscrizioneServizio");
    }

    public void tornaLogin(ActionEvent actionEvent) throws IOException {
        String nomeGenitore1_text= nomeGenitore1_field.getText();
        String cognomeGenitore1_text= cognomeGenitore1_field.getText();
        String emailGenitore1_text= emailGenitore1_field.getText();
        String telefonoGenitore1_text= telefonoGenitore1_field.getText();

        String nomeGenitore2_text= nomeGenitore2_field.getText();
        String cognomeGenitore2_text= cognomeGenitore2_field.getText();
        String emailGenitore2_text= emailGenitore2_field.getText();
        String telefonoGenitore2_text= telefonoGenitore2_field.getText();

        TrasferitoreDati trasferitoreDati= TrasferitoreDati.getIstanza();
        Ragazzo ragazzo= trasferitoreDati.getRagazzo();

        if(!nomeGenitore1_text.isEmpty() && !cognomeGenitore1_text.isEmpty() && ! emailGenitore1_text.isEmpty() && !telefonoGenitore1_text.isEmpty()){
            Genitore genitore= new Genitore(emailGenitore1_text, nomeGenitore1_text, cognomeGenitore1_text, telefonoGenitore1_text);

            ragazzo.getGenitori().add(genitore);

            if(!nomeGenitore2_text.isEmpty() && !cognomeGenitore2_text.isEmpty() && ! emailGenitore2_text.isEmpty() && !telefonoGenitore2_text.isEmpty()){
                Genitore genitore2= new Genitore(emailGenitore2_text, nomeGenitore2_text, cognomeGenitore2_text, telefonoGenitore2_text);

                ragazzo.getGenitori().add(genitore2);

            }
             Request.post("guy", ragazzo);
            App.setRoot("Profilo");
        } else{
            errore_label.setVisible(true);
        }
    }
}
