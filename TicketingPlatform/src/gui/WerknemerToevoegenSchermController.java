package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import domein.controllers.AangemeldeGebruikerController;
import domein.controllers.AdministratorController;
import domein.models.TypeGebruiker;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;

import javafx.scene.control.ChoiceBox;

public class WerknemerToevoegenSchermController extends GridPane {
	@FXML
	private Button btnTerug;
	@FXML
	private Button btnWerknemerAanmaken;
	@FXML
	private Label lblFout;
	@FXML
	private TextField txfNaam;
	@FXML
	private TextField txfVoornaam;
	@FXML
	private TextField txfEmail;
	@FXML
	private TextField txfGsmNummer;
	@FXML
	private TextField txfVasteLijn;
	@FXML
	private TextField txfStraat;
	@FXML
	private TextField txfHuisnummer;
	@FXML
	private TextField txfPostcode;
	@FXML
	private TextField txfStad;
	@FXML
	private ChoiceBox<TypeGebruiker> cboRol;
	@FXML
	private PasswordField txfWachtwoord;
	@FXML
	private PasswordField txfWachtwoordBevestigen;

	private WerknemersBeherenSchermController parent;
	private AangemeldeGebruikerController ac;

	public WerknemerToevoegenSchermController(WerknemersBeherenSchermController werknemersBeherenSchermController,
			AangemeldeGebruikerController ac) {
		this.parent = werknemersBeherenSchermController;
		this.ac = ac;

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("WerknemerToevoegenScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();

			cboRol.setItems(FXCollections.observableArrayList(TypeGebruiker.values()));

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

	// Event Listener on Button[#btnWerknemerAanmaken].onAction
	@FXML
	public void btnWerknemerAanmakenOnAction(ActionEvent event) {

		String samengesteldAdres = txfStraat.getText() + " " + txfHuisnummer.getText() + ", " + txfPostcode.getText()
				+ " " + txfStad.getText();

		try {

			if (txfWachtwoord.getText().equals(txfWachtwoordBevestigen.getText())) {
				if (cboRol.getValue() != null) {
					if (!txfStraat.getText().isBlank() || !txfHuisnummer.getText().isBlank()
							|| !txfPostcode.getText().isBlank() || !txfStad.getText().isBlank()) {
						ac.voegMedewerkerToe(txfNaam.getText(), txfVoornaam.getText(), txfEmail.getText(), new String[] {txfGsmNummer.getText(), txfVasteLijn.getText()}, cboRol.getValue().toString(), txfWachtwoord.getText(),
								samengesteldAdres);
					} else {
						lblFout.setText("Gelieve alle adresgegevens correct in te vullen.");
					}

				} else {
					lblFout.setText("Gelieve een rol te selecteren.");
				}
			} else {
				lblFout.setText("Wachtwoorden zijn niet identiek.");
			}
		} catch (IllegalArgumentException e) {
			lblFout.setText(e.getMessage());
		}

	}
}
