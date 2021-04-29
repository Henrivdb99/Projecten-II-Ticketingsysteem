package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

import domein.models.Gebruiker;
import domein.models.Werknemer;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WerknemerDetailsSchermController extends GridPane {
	@FXML
	private Label lblPersoneelsnummer;
	@FXML
	private Label lblNaam;
	@FXML
	private Label lblVoornaam;
	@FXML
	private Label lblEmailGebruikersnaam;
	@FXML
	private Label lblAdres;
	@FXML
	private Label lblVasteLijnWerk;
	@FXML
	private Label lblGsmNummer;
	@FXML
	private Label lblTijdInDienst;
	@FXML
	private Label lblRol;
	@FXML
	private Label lblStatus;
	@FXML
	private Button btnTerug;

	private WerknemersBeherenSchermController parent;

	public WerknemerDetailsSchermController(WerknemersBeherenSchermController werknemersBeherenSchermController, Gebruiker selectedUser) {
		this.parent = werknemersBeherenSchermController;

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("WerknemerDetailsScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
			
			lblPersoneelsnummer.setText(Integer.toString(selectedUser.getId()));
			lblNaam.setText(selectedUser.getNaam());
			lblVoornaam.setText(selectedUser.getVoornaam());
			lblEmailGebruikersnaam.setText(selectedUser.getEmailAdres());
			lblAdres.setText(selectedUser.getAdres()[0] + " " + selectedUser.getAdres()[1] + ", " + selectedUser.getAdres()[2] + " " + selectedUser.getAdres()[3]);
			lblVasteLijnWerk.setText(selectedUser.getTelefoonnummers()[1]);
			lblGsmNummer.setText(selectedUser.getTelefoonnummers()[0]);
			lblTijdInDienst.setText(selectedUser.getRegistratieDatum().toString());
			
			lblRol.setText(selectedUser.getRol().toString());
			 
			lblStatus.setText(selectedUser.getStatus().toString());
					
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
