package gui;

import static org.junit.jupiter.api.DynamicTest.stream;

import java.io.IOException;

import domein.models.Gebruiker;
import domein.models.GebruikerGegevens;
import domein.models.Klant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class KlantDetailsSchermController extends GridPane {
	@FXML
	private Label lblKlantNummer;
	@FXML
	private Label lblNaam;
	@FXML
	private Label lblVoornaam;
	@FXML
	private Label lblEmailGebruikersnaam;
	@FXML
	private Label lblAdres;
	@FXML
	private Label lblGsmNummer;
	@FXML
	private Label lblVasteLijn;
	@FXML
	private Label lblTijdKlant;
	@FXML
	private Label lblStatus;
	@FXML
	private Label lblBedrijfsnaam;
	@FXML
	private Button btnTerug;

	private KlantenBeherenSchermController parent;

	public KlantDetailsSchermController(KlantenBeherenSchermController klantenBeherenSchermController, GebruikerGegevens selectedUser) {
		this.parent = klantenBeherenSchermController;
		
		((Klant) selectedUser).getTickets().stream().forEach(t -> System.out.println(t.getOmschrijving()));
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("KlantDetailsScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
			
			lblKlantNummer.setText(Integer.toString(selectedUser.getId()));
			lblNaam.setText(selectedUser.getNaam());
			lblVoornaam.setText(selectedUser.getVoornaam());
			lblEmailGebruikersnaam.setText(selectedUser.getEmailAdres());
			lblAdres.setText(selectedUser.getAdres()[0] + " " + selectedUser.getAdres()[1] + ", " + selectedUser.getAdres()[2] + " " + selectedUser.getAdres()[3]);
			lblGsmNummer.setText(selectedUser.getTelefoonnummers()[0]);
			lblVasteLijn.setText(selectedUser.getTelefoonnummers()[1]);
			lblTijdKlant.setText(selectedUser.getRegistratieDatum().toString());
			lblStatus.setText(selectedUser.getStatus().toString());
			lblBedrijfsnaam.setText(selectedUser.getBedrijfsnaam().toString());
					
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	// Event Listener on Button[#btnTerug].onAction
	@FXML
	public void btnTerugOnAction(ActionEvent event) {
		Stage stage = (Stage) (getScene().getWindow());
		stage.setScene(this.parent.getScene());
	}
}
