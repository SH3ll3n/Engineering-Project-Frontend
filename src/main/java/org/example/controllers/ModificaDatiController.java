package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.example.App;
import org.example.entities.Ragazzo;
import org.example.utils.Request;
import org.example.utils.TrasferitoreDati;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ModificaDatiController {

    @FXML
    private TextField nome_text;
    @FXML
    private TextField cognome_text;
    @FXML
    private DatePicker data_text;

    private Ragazzo ragazzo;

    private void setDatePickerDateFormat() {
        data_text.setConverter(new StringConverter<LocalDate>() {
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

    @FXML
    public void initialize() {
        TrasferitoreDati trasferitoreDati = TrasferitoreDati.getIstanza();
        ragazzo = trasferitoreDati.getRagazzo();
        setDatePickerDateFormat();
        nome_text.setText(ragazzo.getNome());
        cognome_text.setText(ragazzo.getCognome());
        data_text.setValue(ragazzo.getDataNascita());
    }

    public void tornaIndietro(ActionEvent actionEvent) throws IOException {
        App.setRoot("Profilo");
    }

    public void tornaLogin(ActionEvent actionEvent) throws IOException {
        String nome=nome_text.getText();
        String cognome=cognome_text.getText();
        LocalDate data=data_text.getValue();

        ragazzo.setNome(nome);
        ragazzo.setCognome(cognome);
        ragazzo.setDataNascita(data);
        Request.put("guy/modifica",ragazzo);
        App.setRoot("Login");
    }
}
