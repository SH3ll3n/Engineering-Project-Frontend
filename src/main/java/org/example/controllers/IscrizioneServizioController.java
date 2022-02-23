package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.example.App;
import org.example.entities.Allergia;
import org.example.entities.AllergiaId;
import org.example.entities.Ragazzo;
import org.example.utils.TrasferitoreDati;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IscrizioneServizioController {

    @FXML
    TextField nome_field;
    @FXML
    TextField cognome_field;
    @FXML
    TextField email_field;
    @FXML
    TextField password_field;
    @FXML
    DatePicker dataNascita_field;
    @FXML
    TextField indirizzo_field;
    @FXML
    TextField cellulare_field;
    @FXML
    TextField hobby1_field;
    @FXML
    TextField hobby2_field;
    @FXML
    TextField hobby3_field;
    @FXML
    TextField allergia_field;
    @FXML
    TextField precauzione_field;
    @FXML
    TextField allergia1_field;
    @FXML
    TextField precauzione1_field;
    @FXML
    Label errore_field;

    //funzione che modifica DatePicker(calendario) usando dal formato americano a quello europeo
    private void setDatePickerDateFormat() {
        dataNascita_field.setConverter(new StringConverter<LocalDate>() {
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

    public void initialize(){
        setDatePickerDateFormat();
    }

    @FXML
    public void compilaGenitori() throws IOException {
        String nome_text = nome_field.getText();
        String cognome_text = cognome_field.getText();
        String email_text = email_field.getText();
        String password_text = password_field.getText();
        String indirizzo_text = indirizzo_field.getText();
        String cellulare_text = cellulare_field.getText();
        LocalDate data_text = dataNascita_field.getValue();
        String hobby1_text = hobby1_field.getText();
        String hobby2_text = hobby2_field.getText();
        String hobby3_text = hobby3_field.getText();
        String string_hobby_text = hobby1_text + ";" + hobby2_text + ";" + hobby3_text;
        String allergia_text = allergia_field.getText();
        String precauzione_text = precauzione_field.getText();
        String allergia1_text = allergia1_field.getText();
        String precauzione1_text = precauzione1_field.getText();

        if (!nome_text.isEmpty() && !cognome_text.isEmpty() && !email_text.isEmpty() && !password_text.isEmpty() && !indirizzo_text.isEmpty() && data_text!=null && !hobby1_text.isEmpty()) {
            Ragazzo ragazzo = new Ragazzo(email_text, nome_text, cognome_text, data_text, indirizzo_text, password_text, cellulare_text, string_hobby_text);

            if (!allergia_text.isEmpty() && !precauzione_text.isEmpty()) {
                Allergia allergia = new Allergia(new AllergiaId(allergia_text), precauzione_text);
                //aggiungo alla lista le allergie
                ragazzo.getAllergie().add(allergia);
            }

            if (!allergia1_text.isEmpty() && !precauzione1_text.isEmpty()) {
                Allergia allergia = new Allergia(new AllergiaId(allergia1_text), precauzione1_text);
                //aggiungo alla lista le allergie
                ragazzo.getAllergie().add(allergia);
            }

            //metto il ragazzo nel trasferitore
            TrasferitoreDati trasferitoreDati = TrasferitoreDati.getIstanza();
            trasferitoreDati.setRagazzo(ragazzo);

            App.setRoot("DatiGenitori");
        } else{
            errore_field.setVisible(true);
        }
    }

    @FXML
    private void tornaLogin() throws IOException {
        App.setRoot("Login");
    }
}
