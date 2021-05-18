package gui;

import java.io.IOException;

import domein.controllers.AangemeldeGebruikerController;
import domein.models.GebruikerGegevens;
import domein.models.GebruikerStatus;
import domein.models.WerknemerRol;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class KlantWijzigenSchermController extends GridPane {
	
	@FXML
	private Button btnKlantAanmaken;
	@FXML
	private Label lblFout;
	@FXML
	private Label lblTitel;
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
	private TextField txfBedrijfsnaam;
	@FXML
	private PasswordField txfWachtwoord;
	@FXML
	private PasswordField txfWachtwoordBevestigen;
	@FXML
	private ChoiceBox<GebruikerStatus> cboStatus;
	@FXML
	private Label lblStatus;

	// Event Listener on Button[#btnTerug].onAction
	private KlantenBeherenSchermController parent;
	private GebruikerGegevens selectedUser;
	private AangemeldeGebruikerController ac;

	public KlantWijzigenSchermController(KlantenBeherenSchermController klantenBeherenSchermController,
			GebruikerGegevens selectedUser, AangemeldeGebruikerController ac) {
		this.parent = klantenBeherenSchermController;
		this.selectedUser = selectedUser;
		this.ac = ac;

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("KlantToevoegenScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();

			cboStatus.setItems(FXCollections.observableArrayList(GebruikerStatus.values()));
			lblTitel.setText("Klant wijzigen");
			btnKlantAanmaken.setText("Klant wijzigen");

			txfNaam.setText(selectedUser.getNaam());
			txfVoornaam.setText(selectedUser.getVoornaam());
			txfEmail.setText(selectedUser.getEmailAdres());
			txfGsmNummer.setText(selectedUser.getTelefoonnummers()[0]);
			txfVasteLijn.setText(selectedUser.getTelefoonnummers()[1]);
						
			cboStatus.setValue(selectedUser.getStatus());
			txfStraat.setText(selectedUser.getAdres()[0]);
			txfHuisnummer.setText(selectedUser.getAdres()[1]);
			txfPostcode.setText(selectedUser.getAdres()[2]);
			txfStad.setText(selectedUser.getAdres()[3]);
			txfBedrijfsnaam.setText(selectedUser.getBedrijfsnaam());
			
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	// Event Listener on Button[#btnKlantAanmaken].onAction
	@FXML
	public void btnKlantAanmakenOnAction(ActionEvent event) {

		try {

			if (txfWachtwoord.getText().equals(txfWachtwoordBevestigen.getText())) {
				if (cboStatus.getValue() != null) {
					if (!txfStraat.getText().isBlank() || !txfHuisnummer.getText().isBlank()
							|| !txfPostcode.getText().isBlank() || !txfStad.getText().isBlank()) {
						ac.wijzigKlant(selectedUser.getId(), txfNaam.getText(), txfVoornaam.getText(),
								txfEmail.getText(), new String[] { txfGsmNummer.getText(), txfVasteLijn.getText() },
							 cboStatus.getValue(), txfWachtwoord.getText(),
								new String[] { txfStraat.getText(), txfHuisnummer.getText(), txfPostcode.getText(),
										txfStad.getText() }, txfBedrijfsnaam.getText());
						//return
						Stage stage = (Stage) (getScene().getWindow());
						stage.setScene(this.parent.getScene());
						
					} else {
						lblFout.setText("Gelieve alle bedrijfsgegevens correct in te vullen.");
					}
				} else {
					lblFout.setText("Gelieve een status te selecteren.");
				}
			} else {
				lblFout.setText("Wachtwoorden zijn niet identiek.");
			}
		} catch (IllegalArgumentException e) {
			lblFout.setText(e.getMessage());
		}

	}
}
