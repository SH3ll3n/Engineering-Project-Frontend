package org.example.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.entities.*;
import org.example.utils.Request;
import org.example.utils.TrasferitoreDati;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class AlloggioController {

	private static List<Ragazzo> ragazzi;
	@FXML
	private CheckBox cameraSingola;
	@FXML
	private CheckBox cameraCondivisa;
	@FXML
	private TextField nomeAmico;
	@FXML
	private TextField emailAmico;
	//ottengo il singleton, l'oggetto di tipo trasferitore dati
	private final TrasferitoreDati trasferitoreDati = TrasferitoreDati.getIstanza();

	public void tornaGita(ActionEvent actionEvent) throws IOException {
		App.setRoot("Gita");
	}

	public void VaiCollege(ActionEvent actionEvent) throws IOException {
		//salvo i dati
		Ragazzo ragazzo = trasferitoreDati.getRagazzo();
		RagazzoVacanza ragazzoVacanza = trasferitoreDati.getRagazzoVacanza();
		List<College> colleges = getDataCollege();
		List<RagazzoVacanza> ragazziVacanze = getDataRagazziVacanze();
		Random random = new Random();
		List<RagazzoVacanza> ragazziVacanze_college = ragazziVacanze.stream().filter(r -> r.getCollege() != null).collect(Collectors.toList());

		if (!ragazziVacanze_college.isEmpty()) {
			ragazzoVacanza.setCollege(ragazziVacanze.get(0).getCollege());
		} else {
			List<College> emptyColleges = colleges.stream().filter(c -> c.getRagazziVacanza().isEmpty()).collect(Collectors.toList());
			College college = emptyColleges.get(random.nextInt(emptyColleges.size()));
			ragazzoVacanza.setCollege(college);
		}

		if (cameraSingola.isSelected()) {
			ragazzoVacanza.setTipo_Camera(Tipo.Stanza_Singola);
		} else {
			List<RagazzoVacanza> ragazziVacanze_college_condivisa = ragazziVacanze_college.stream().filter(rv1 ->
					rv1.getTipo_Camera().equals(Tipo.Stanza_Condivisa)).collect(Collectors.toList());
			if (!ragazziVacanze_college_condivisa.isEmpty()) {
				RagazzoVacanza compagno_rv = ragazziVacanze_college_condivisa.get(random.nextInt(ragazziVacanze_college_condivisa.size()));
				StringBuilder sb = Request.get("guy/" + compagno_rv.getRagazzoVacanzaId().getEmailRagazzo());
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				Ragazzo amico = objectMapper.readValue(sb.toString(), Ragazzo.class);
				ragazzoVacanza.setNome_Amico(amico.getNome());
				ragazzoVacanza.setEmail_Amico(amico.getEmail());

				compagno_rv.setNome_Amico(ragazzo.getNome());
				compagno_rv.setEmail_Amico(ragazzo.getEmail());
				compagno_rv.setRagazzo(amico);
				//put per passargli il compagno_rv aggiornato
				Request.put("/ragazzoVacanza/update", compagno_rv);
			}
			ragazzoVacanza.setTipo_Camera(Tipo.Stanza_Condivisa);
		}
		trasferitoreDati.setRagazzoVacanza(ragazzoVacanza);
		trasferitoreDati.setFamiglia(null);
		App.setRoot("Pagamento");
	}

	public void VaiFamiglia(ActionEvent actionEvent) throws IOException {
		//salvo i dati
		List<Famiglia> famiglie = getDataFamiglie();
		List<RagazzoVacanza> ragazziVacanze = getDataRagazziVacanze();

		Ragazzo ragazzo = trasferitoreDati.getRagazzo();
		RagazzoVacanza ragazzoVacanza = trasferitoreDati.getRagazzoVacanza();

		//controllo che il compagno inserito sia già iscritto per metterlo come compagno
		if (!nomeAmico.getText().isEmpty() && !emailAmico.getText().isEmpty()) {
			ragazziVacanze = ragazziVacanze.stream().map(ragazzoVacanza1 -> {
				StringBuilder sb = null;
				try {
					sb = Request.get("guy/" + ragazzoVacanza1.getRagazzoVacanzaId().getEmailRagazzo());
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.registerModule(new JavaTimeModule());
					Ragazzo r = objectMapper.readValue(sb.toString(), Ragazzo.class);
					ragazzoVacanza1.setRagazzo(r);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return ragazzoVacanza1;
			}).collect(Collectors.toList());
			Optional<RagazzoVacanza> compagnoOptional = ragazziVacanze.stream().filter(
					r -> r.getRagazzo().getEmail().equals(emailAmico.getText()) &&
							r.getRagazzo().getNome().equals(nomeAmico.getText())
			).findFirst();
			if (compagnoOptional.isPresent()) {
				Famiglia famiglia = compagnoOptional.get().getFamiglia();
				ragazzoVacanza.setFamiglia(famiglia);
				ragazzoVacanza.setNome_Amico(nomeAmico.getText());
				ragazzoVacanza.setEmail_Amico(emailAmico.getText());
				compagnoOptional.get().setNome_Amico(ragazzo.getNome());
				compagnoOptional.get().setEmail_Amico(ragazzo.getEmail());
				//put per passargli il compagno aggiornato
				Request.put("/ragazzoVacanza/update", compagnoOptional.get());
				//prendo compagno a caso tra quelli presenti nel DB
			} else {
				Random random = new Random();
				//prendo ragazziVacanze nel DB che sono nella famiglia
				List<RagazzoVacanza> ragazziVacanza_famiglia = ragazziVacanze.stream().filter(
						r -> r.getFamiglia() != null
				).collect(Collectors.toList());
				if (!ragazziVacanza_famiglia.isEmpty()) {
					RagazzoVacanza compagno_rv = ragazziVacanza_famiglia.get(random.nextInt(ragazziVacanza_famiglia.size()));
					StringBuilder sb = Request.get("guy/" + compagno_rv.getRagazzoVacanzaId().getEmailRagazzo());
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.registerModule(new JavaTimeModule());
					Ragazzo amico = objectMapper.readValue(sb.toString(), Ragazzo.class);
					Famiglia famiglia = compagno_rv.getFamiglia();
					ragazzoVacanza.setFamiglia(famiglia);
					ragazzoVacanza.setNome_Amico(amico.getNome());
					ragazzoVacanza.setEmail_Amico(amico.getEmail());
					compagno_rv.setNome_Amico(ragazzo.getNome());
					compagno_rv.setEmail_Amico(ragazzo.getEmail());
					compagno_rv.setRagazzo(amico);
					//put per passargli il compagno_rv aggiornato
					Request.put("/ragazzoVacanza/update", compagno_rv);
				} else {
					List<Famiglia> famiglie_NoRagazzi = famiglie.stream().filter(famiglia ->
							famiglia.getRagazziVacanza().isEmpty()).collect(Collectors.toList());
					Famiglia famiglia = famiglie_NoRagazzi.get(random.nextInt(famiglie.size()));
					ragazzoVacanza.setFamiglia(famiglia);
				}
			}
		} else {
			Random random = new Random();
			List<Famiglia> famiglie_NoRagazzi = famiglie.stream().filter(famiglia ->
					famiglia.getRagazziVacanza().isEmpty()).collect(Collectors.toList());
			Famiglia famiglia = famiglie_NoRagazzi.get(random.nextInt(famiglie.size()));
			ragazzoVacanza.setFamiglia(famiglia);
		}
		ragazzoVacanza.setTipo_Camera(null);
		trasferitoreDati.setRagazzo(ragazzo);
		App.setRoot("Pagamento");
	}

	private List<Famiglia> getDataFamiglie() throws IOException {
		StringBuilder stringBuilder1 = Request.get("famiglia");
		String jsonString1 = stringBuilder1.toString();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		//converti stringa in oggetto
		return objectMapper.readValue(jsonString1, new TypeReference<List<Famiglia>>() {
		});
	}

	private List<College> getDataCollege() throws IOException {
		StringBuilder stringBuilder = Request.get("college");
		String jsonString = stringBuilder.toString();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		//converti stringa in oggetto
		return objectMapper.readValue(jsonString, new TypeReference<List<College>>() {
		});
	}

	public List<RagazzoVacanza> getDataRagazziVacanze() throws IOException {
		Vacanza vacanza = trasferitoreDati.getRagazzoVacanza().getVacanza();
		StringBuilder stringBuilder = Request.get("ragazzoVacanza/" + vacanza.getId());
		String jsonString = stringBuilder.toString();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		//converti stringa in oggetto
		List<RagazzoVacanza> ragazziVacanze = objectMapper.readValue(jsonString, new TypeReference<>() {
		});
		return ragazziVacanze;
	}

	@FXML
	public void initialize() throws IOException {
		StringBuilder stringBuilder = Request.get("guy");
		String jsonString = stringBuilder.toString();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		//converti stringa in oggetto
		List<Ragazzo> ragazzi = objectMapper.readValue(jsonString, new TypeReference<List<Ragazzo>>() {
		});

		cameraCondivisa.setSelected(true);
	}

	public void selezionoCameraSingola(ActionEvent actionEvent) {
		if (cameraCondivisa.isSelected()) {
			cameraCondivisa.setSelected(false);
		}
	}

	public void selezionoCameraCondivisa(ActionEvent actionEvent) {
		if (cameraSingola.isSelected()) {
			cameraSingola.setSelected(false);
		}
	}
}
