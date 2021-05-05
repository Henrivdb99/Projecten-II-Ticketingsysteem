package gui;

import java.io.IOException;

import domein.controllers.AangemeldeGebruikerController;
import domein.models.GebruikerGegevens;
import domein.models.GebruikerStatus;
import domein.models.TypeGebruiker;
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

public class WerknemerWijzigenSchermController extends GridPane {
	
	@FXML
	private Button btnWerknemerAanmaken;
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
	private ChoiceBox<TypeGebruiker> cboRol;
	@FXML
	private PasswordField txfWachtwoord;
	@FXML
	private PasswordField txfWachtwoordBevestigen;
	@FXML
	private ChoiceBox<GebruikerStatus> cboStatus;
	@FXML
	private Label lblStatus;

	// Event Listener on Button[#btnTerug].onAction
	private WerknemersBeherenSchermController parent;
	private GebruikerGegevens selectedUser;
	private AangemeldeGebruikerController ac;

	public WerknemerWijzigenSchermController(WerknemersBeherenSchermController werknemersBeherenSchermController,
			GebruikerGegevens selectedUser, AangemeldeGebruikerController ac) {
		this.parent = werknemersBeherenSchermController;
		this.selectedUser = selectedUser;
		this.ac = ac;

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("WerknemerToevoegenScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();

			cboRol.setItems(FXCollections.observableArrayList(TypeGebruiker.values()));
			cboStatus.setItems(FXCollections.observableArrayList(GebruikerStatus.values()));
			lblTitel.setText("Werknemer wijzigen");
			btnWerknemerAanmaken.setText("Werknemer wijzigen");

			txfNaam.setText(selectedUser.getNaam());
			txfVoornaam.setText(selectedUser.getVoornaam());
			txfEmail.setText(selectedUser.getEmailAdres());
			txfGsmNummer.setText(selectedUser.getTelefoonnummers()[0]);
			txfVasteLijn.setText(selectedUser.getTelefoonnummers()[1]);
			
			cboRol.setValue(selectedUser.getRol()); 
			
			cboStatus.setValue(selectedUser.getStatus());
			txfStraat.setText(selectedUser.getAdres()[0]);
			txfHuisnummer.setText(selectedUser.getAdres()[1]);
			txfPostcode.setText(selectedUser.getAdres()[2]);
			txfStad.setText(selectedUser.getAdres()[3]);

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	// Event Listener on Button[#btnWerknemerAanmaken].onAction
	@FXML
	public void btnWerknemerAanmakenOnAction(ActionEvent event) {

		try {

			if (txfWachtwoord.getText().equals(txfWachtwoordBevestigen.getText())) {
				if (cboRol.getValue() != null) {
					if (cboStatus.getValue() != null) {
						if (!txfStraat.getText().isBlank() || !txfHuisnummer.getText().isBlank()
								|| !txfPostcode.getText().isBlank() || !txfStad.getText().isBlank()) {
							ac.wijzigWerknemer(selectedUser.getId(), txfNaam.getText(), txfVoornaam.getText(),
									txfEmail.getText(), new String[] { txfGsmNummer.getText(), txfVasteLijn.getText() },
									cboRol.getValue(), cboStatus.getValue(), txfWachtwoord.getText(),
									new String[] { txfStraat.getText(), txfHuisnummer.getText(), txfPostcode.getText(),
											txfStad.getText() });
							//return
							Stage stage = (Stage) (getScene().getWindow());
							stage.setScene(this.parent.getScene());
							
						} else {
							lblFout.setText("Gelieve alle adresgegevens correct in te vullen.");
						}
					} else {
						lblFout.setText("Gelieve een status te selecteren.");
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
