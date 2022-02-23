package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.App;
import org.example.entities.*;
import org.example.utils.TrasferitoreDati;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public class ProfiloController {

    @FXML
    TableView tabella_dati;
    @FXML
    TableColumn nome_column;
    @FXML
    TableColumn cognome_column;
    @FXML
    TableColumn data_column;
    @FXML
    TableView<DatiVacanzaProfilo> tabella_vacanze;
    @FXML
    TableColumn citta_column;
    @FXML
    TableColumn data_Partenza_column;
    @FXML
    TableColumn numero_settimane_column;
    @FXML
    TableColumn lingua_studiata_column;
    @FXML
    TableColumn alloggio_column;
    @FXML
    TableColumn compagno_column;
    @FXML
    Button questionario_button;

    @FXML
    public void initialize() {

        TrasferitoreDati trasferitoreDati = TrasferitoreDati.getIstanza();
        Ragazzo ragazzo = trasferitoreDati.getRagazzo();

        nome_column.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cognome_column.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        data_column.setCellValueFactory(new PropertyValueFactory<>("dataNascita"));

        citta_column.setCellValueFactory(new PropertyValueFactory<>("citta"));
        data_Partenza_column.setCellValueFactory(new PropertyValueFactory<>("data"));
        numero_settimane_column.setCellValueFactory(new PropertyValueFactory<>("numeroSettimane"));
        lingua_studiata_column.setCellValueFactory(new PropertyValueFactory<>("linguaStudiata"));
        alloggio_column.setCellValueFactory(new PropertyValueFactory<>("alloggio"));
        compagno_column.setCellValueFactory(new PropertyValueFactory<>("compagno"));

        //puntatore tabella
        tabella_dati.getItems().add(ragazzo);

        if (ragazzo.getRagazziVacanze().isEmpty()) {
            questionario_button.setVisible(false);
        } else {
            Set<RagazzoVacanza> ragazziVacanze = ragazzo.getRagazziVacanze();
            ragazziVacanze.forEach(ragazzoVacanza -> {
                tabella_vacanze.getItems().add(new DatiVacanzaProfilo(ragazzoVacanza));
                Vacanza vacanza = ragazzoVacanza.getVacanza();
                LocalDate dataFine = vacanza.getData().plusWeeks(
                        vacanza.getNumeroSettimane()
                );
                if (dataFine.isBefore(LocalDate.now())) {
                    questionario_button.setVisible(true);
                }
            });
        }

        tabella_vacanze.setOnMousePressed(new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    DatiVacanzaProfilo datiVacanzaProfilo = tabella_vacanze.getSelectionModel().getSelectedItem();
                    LocalDate dataFine = datiVacanzaProfilo.getData().plusWeeks(
                            datiVacanzaProfilo.getNumeroSettimane()
                    );
                    if (dataFine.isBefore(LocalDate.now())) {
                        Set<RagazzoVacanza> ragazziVacanze = ragazzo.getRagazziVacanze();
                        Optional<RagazzoVacanza> ragazzoVacanza = ragazziVacanze.stream().filter(
                                rv -> rv.getRagazzoVacanzaId()
                                        .equals(datiVacanzaProfilo.getRagazzoVacanzaId())
                        ).findFirst();
                        if (ragazzoVacanza.get().getQuestionario() != null) {
                            String a1 = "https://drive.google.com/file/d/1zfh_DP8CfRD7x9q0KsfDommXk-4UWWol/view?usp=sharing";
                            String a2 = "https://drive.google.com/file/d/1wHPE3PER7vaPvWceN-dHyKIGatUjCMET/view?usp=sharing";
                            String b1 = "https://drive.google.com/file/d/103b_pDmpjUEbuMZtaoQLDhEA2_k7VAhc/view?usp=sharing";
                            String b2 = "https://drive.google.com/file/d/1pxmq9R4xzNKAUD4_F-yy5b9mEOy4SXPr/view?usp=sharing";
                            String c1 = "https://drive.google.com/file/d/129Dle5mb4hpH1rrOY6BQWTJKOY9VWARL/view?usp=sharing";
                            String c2 = "https://drive.google.com/file/d/10FQSvEJHRPOBIiVyP4X5BGJ0QFEdTkAX/view?usp=sharing";
                            switch (ragazzoVacanza.get().getLivello()) {
                                case A1:
                                    App.openLink(a1);
                                    break;
                                case A2:
                                    App.openLink(a2);
                                    break;
                                case B1:
                                    App.openLink(b1);
                                    break;
                                case B2:
                                    App.openLink(b2);
                                    break;
                                case C1:
                                    App.openLink(c1);
                                    break;
                                case C2:
                                    App.openLink(c2);
                                    break;
                            }
                        }
                    }
                }
            }
        });
        tabella_dati.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    try {
                        App.setRoot("ModificaDati");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void vaiQuestionario(ActionEvent actionEvent) throws IOException {
        App.setRoot("CompilazioneQuestionario", 736, 560);
    }

    public void vaiVacanza(ActionEvent actionEvent) throws IOException {
        App.setRoot("Vacanze");
    }

    public void tornaLogin(ActionEvent actionEvent) throws IOException {
        App.setRoot("Login");
    }
}
