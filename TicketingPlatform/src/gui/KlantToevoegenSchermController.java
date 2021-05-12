package gui;

import java.io.IOException;

import domein.controllers.AangemeldeGebruikerController;
import domein.models.GebruikerStatus;
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

public class KlantToevoegenSchermController extends GridPane {
	@FXML
	private Button btnTerug;
	@FXML
	private Button btnKlantAanmaken;
	@FXML
	private Label lblFout;
	@FXML
	private Label lblStatus;
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
	private  ChoiceBox<GebruikerStatus>  cboStatus;

	private KlantenBeherenSchermController parent;
	private AangemeldeGebruikerController ac;

	public KlantToevoegenSchermController(KlantenBeherenSchermController klantenBeherenSchermController,
			AangemeldeGebruikerController ac) {
		this.parent = klantenBeherenSchermController;
		this.ac = ac;

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("KlantToevoegenScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
			
			//status wordt niet ingevuld door gebruiker bij aanmaken
			cboStatus.setVisible(false); 
			lblStatus.setVisible(false);

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

	// Event Listener on Button[#btnKlantAanmaken].onAction
	@FXML
	public void btnKlantAanmakenOnAction(ActionEvent event) {

		//String samengesteldAdres = txfStraat.getText() + " " + txfHuisnummer.getText() + ", " + txfPostcode.getText()
		//		+ " " + txfStad.getText();

		try {

			if (txfWachtwoord.getText().equals(txfWachtwoordBevestigen.getText())) {
				if (!txfStraat.getText().isBlank() || !txfHuisnummer.getText().isBlank()
						|| !txfPostcode.getText().isBlank() || !txfStad.getText().isBlank()) {
					
					ac.voegKlantToe(txfNaam.getText(), txfVoornaam.getText(), txfEmail.getText(), new String[] {txfGsmNummer.getText(), txfVasteLijn.getText()}, txfWachtwoord.getText(),
							new String[] {txfStraat.getText(), txfHuisnummer.getText(), txfPostcode.getText(), txfStad.getText()}, txfBedrijfsnaam.getText());
					
					resetVelden();
					lblFout.setText("Aanmaken klant gelukt!");
				} else {
					lblFout.setText("Gelieve alle adresgegevens correct in te vullen.");
				}

			} else {
				lblFout.setText("Wachtwoorden zijn niet identiek.");
			}
		} catch (IllegalArgumentException e) {
			lblFout.setText(e.getMessage());
		}

	}
	
	private void resetVelden() {		

		txfNaam.clear();
		txfVoornaam.clear();
		txfEmail.clear();
		txfGsmNummer.clear();
		txfVasteLijn.clear();
		
		txfWachtwoord.clear();
		txfWachtwoordBevestigen.clear();
		
		txfBedrijfsnaam.clear();
		txfStraat.clear();
		txfHuisnummer.clear();
		txfPostcode.clear();
		txfStad.clear();
	}
}
