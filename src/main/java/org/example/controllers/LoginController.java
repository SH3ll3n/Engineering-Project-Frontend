package org.example.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.entities.Ragazzo;
import org.example.entities.Responsabile;
import org.example.utils.Request;
import org.example.utils.TrasferitoreDati;

import java.io.IOException;
import java.util.List;

public class LoginController {

    @FXML
    TextField username_field;
    @FXML
    PasswordField password_field;
    @FXML
    Label status_label;

    public void loginAction() throws IOException {
        String username_text = username_field.getText();
        String password_text = password_field.getText();

        TrasferitoreDati trasferitoreDati= TrasferitoreDati.getIstanza();

        List<Ragazzo> ragazzi = getDataRagazzi();
        ragazzi.forEach(ragazzo -> {
            String email = ragazzo.getEmail();
            String password = ragazzo.getPassword();
            if (username_text.equals(email) && password_text.equals(password)) {
                try {
                    trasferitoreDati.setRagazzo(ragazzo);
                    App.setRoot("Profilo");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                status_label.setText("LOGIN STATUS:username o password errati");
            }
        });

        List<Responsabile> responsabili=getDataResponsabili();
        responsabili.forEach (responsabile -> {
            String email=responsabile.getEmail();
            String password=responsabile.getPassword();
            if(username_text.equals(email) && password_text.equals(password)) {
                try {
                    App.setRoot("ElencoVacanzeResponsabile");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                    status_label.setText("LOGIN STATUS:username o password errati");
                }
        });
    }

    public List<Ragazzo> getDataRagazzi() throws IOException {
        StringBuilder stringBuilder = Request.get("guy");
        String jsonString = stringBuilder.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        //converti stringa in oggetto
        List<Ragazzo> ragazzi = objectMapper.readValue(jsonString, new TypeReference<List<Ragazzo>>() {
        });
        return ragazzi;
    }

    public List<Responsabile> getDataResponsabili() throws IOException {
        StringBuilder stringBuilder = Request.get("responsabile");
        String jsonString = stringBuilder.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        //converti stringa in oggetto
        List<Responsabile> responsabili = objectMapper.readValue(jsonString, new TypeReference<List<Responsabile>>() {
        });
        return responsabili;
    }


    @FXML
    private void loginIscrizione() throws IOException {
        App.setRoot("IscrizioneServizio");
    }
}
