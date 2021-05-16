package gui;

import java.io.IOException;

import domein.models.Ticket;
import domein.models.TicketGegevens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TicketDetailsSchermController extends GridPane{
	@FXML
	private Label lblDatumAangemaakt;
	@FXML
	private Label lblTitel;
	@FXML
	private Label lblType;
	@FXML
	private Label lblKlantnummer;
	@FXML
	private Label lblNaamContactpersoon;
	@FXML
	private Label lblOmschrijving;
	@FXML
	private Label lblOpmerkingen;
	@FXML
	private Label lblToegewezenTechnieker;
	@FXML
	private Label lblVoornaamContactpersoon;
	@FXML
	private Label lblEmailAdres;
	@FXML
	private Label lblGsmnummer;
	@FXML
	private Label lblVastelijn;
	@FXML
	private Button btnBijlagesWeergeven;
	
	public TicketDetailsSchermController(TicketsBeherenSchermController ticketsBeherenSchermController,
			TicketGegevens selectedTicket) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TicketDetailsScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
			
			lblDatumAangemaakt.setText(selectedTicket.getDatumAanmaken().toString());
			lblTitel.setText(selectedTicket.getTitel());
			lblType.setText(String.valueOf(selectedTicket.getTypeTicket()));
			lblEmailAdres.setText(selectedTicket.getKlant().getEmailAdres());
			lblGsmnummer.setText(selectedTicket.getKlant().getTelefoonnummers()[0]);
			lblVastelijn.setText(selectedTicket.getKlant().getTelefoonnummers()[1]);
			lblKlantnummer.setText(Integer.toString(selectedTicket.getKlant().getId()));
			lblNaamContactpersoon.setText(selectedTicket.getKlant().getNaam());
			lblOmschrijving.setText(selectedTicket.getOmschrijving());
			lblOpmerkingen.setText(selectedTicket.getOpmerkingen());
			lblToegewezenTechnieker.setText(selectedTicket.getTechnieker().getNaam() + " " + selectedTicket.getTechnieker().getVoornaam());
			lblVoornaamContactpersoon.setText(selectedTicket.getKlant().getVoornaam());
			
			//NOG AAN VERDERWEREKN 
			btnBijlagesWeergeven.setDisable(true);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	@FXML
	public void btnBijlagesWeergevenOnAction(ActionEvent event) {

	}

}
