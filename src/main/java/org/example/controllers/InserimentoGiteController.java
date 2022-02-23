package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.example.App;
import org.example.entities.Gita;
import org.example.entities.GitaId;
import org.example.utils.TrasferitoreDati;

import java.io.IOException;

public class InserimentoGiteController {

    @FXML
    private TextField destinazione;
    @FXML
    private TextField costo;
    @FXML
    private TextField numeroOre;
    @FXML
    private TextField descrizione;
    @FXML
    private Label errore;

    public void tornaVacanza(ActionEvent actionEvent) throws IOException {
        App.setRoot("VacanzeResponsabile", 707,480);
    }

    public void confermoVacanze(ActionEvent actionEvent) throws IOException {
        if (!destinazione.getText().isEmpty() && !costo.getText().isEmpty() && !numeroOre.getText().isEmpty() &&
                !descrizione.getText().isEmpty()) {
            String destinazioneText = destinazione.getText();
            String costoText = costo.getText();
            String numeroOreText = numeroOre.getText();
            String descrizioneText = descrizione.getText();

            Gita gita=new Gita(
                    new GitaId(destinazioneText),
                    descrizioneText,
                    Integer.parseInt(costoText),
                    Integer.parseInt(numeroOreText)
            );
            TrasferitoreDati trasferitoreDati= TrasferitoreDati.getIstanza();
            trasferitoreDati.getGite().add(gita);

            App.setRoot("VacanzeResponsabile", 707,480);
        } else {
            errore.setVisible(true);
        }
    }
}
