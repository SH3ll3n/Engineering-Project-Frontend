package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import org.example.App;
import org.example.entities.Livello;
import org.example.entities.Pagamento;
import org.example.entities.Ragazzo;
import org.example.entities.RagazzoVacanza;
import org.example.utils.Request;
import org.example.utils.TrasferitoreDati;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PagamentoController {

    @FXML
    private CheckBox carta;
    @FXML
    private CheckBox bonifico;
    @FXML
    private Label errore_label;

    private TrasferitoreDati trasferitoreDati=TrasferitoreDati.getIstanza();

    public void cartaAction(ActionEvent actionEvent) {
        if(bonifico.isSelected()){
            bonifico.setSelected(false);
        }
        RagazzoVacanza ragazzoVacanza=trasferitoreDati.getRagazzoVacanza();
        Random random=new Random();
        List<Livello> livelli= Arrays.asList(Livello.values());
        ragazzoVacanza.setLivello(livelli.get(random.nextInt(livelli.size())));
        ragazzoVacanza.setPagamento(Pagamento.carta);
    }

    public void bonificoAction(ActionEvent actionEvent) {
        if(carta.isSelected()){
            carta.setSelected(false);
        }
        RagazzoVacanza ragazzoVacanza=trasferitoreDati.getRagazzoVacanza();
        Random random=new Random();
        List<Livello> livelli= Arrays.asList(Livello.values());
        ragazzoVacanza.setLivello(livelli.get(random.nextInt(livelli.size())));
        ragazzoVacanza.setPagamento(Pagamento.bonifico);
    }

    public void prosegui(ActionEvent actionEvent) throws IOException {
      if(bonifico.isSelected() || carta.isSelected()){
          RagazzoVacanza ragazzoVacanza=trasferitoreDati.getRagazzoVacanza();
          Ragazzo ragazzo=trasferitoreDati.getRagazzo();
          ragazzo.getRagazziVacanze().add(ragazzoVacanza);
          Request.put("guy", ragazzo);
          App.setRoot("Login");
      }else{
          errore_label.setVisible(true);
      }
    }

    public void tornaAlloggio(ActionEvent actionEvent) throws IOException {
        App.setRoot("Alloggio");
    }
}
