package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import domein.controllers.AangemeldeGebruikerController;
import domein.models.GebruikerGegevens;
import domein.models.Ticket;
import domein.models.TicketGegevens;
import domein.models.TicketStatus;
import domein.models.WerknemerRol;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.control.ChoiceBox;

public class TicketWijzigenSchermController extends GridPane implements Initializable {
	@FXML
	private Label lblTitel;
	@FXML
	private Button btnKlantAanmaken;
	@FXML
	private Label lblFout;
	@FXML
	private TextField txfTitel;
	@FXML
	private TextField txfKlantId;
	@FXML
	private Label lblStatus;
	@FXML
	private ChoiceBox<Integer> cboType;
	@FXML
	private Button btnBestandenToevoegen;
	@FXML
	private Label lblBijlage;
	@FXML
	private TextArea txaOmschrijving;
	@FXML
	private TextArea txaOpmerkingen;
	@FXML
	private ChoiceBox<GebruikerGegevens> cboTechnieker;
	@FXML
	private ChoiceBox<TicketStatus> cboStatus;
	
	private AangemeldeGebruikerController ac;
	private TicketGegevens selectedTicket;
	
	public TicketWijzigenSchermController(TicketsBeherenSchermController ticketsBeherenSchermController,
			TicketGegevens selectedTicket, AangemeldeGebruikerController ac) {
		try {
			this.ac=ac;
			this.selectedTicket = selectedTicket;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TicketToevoegenScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
			
<<<<<<< Upstream, based on branch 'main' of https://github.com/HoGentProjectenII/2021-java-g-23.git
=======
			cboTechnieker.setItems(ac.geefTechniekers());
			cboStatus.setItems(FXCollections.observableArrayList(TicketStatus.values()));
			cboType.setItems(FXCollections.observableArrayList(new Integer[] {1, 2, 3}));	
			btnKlantAanmaken.setText("Ticket wijzigen");
			lblTitel.setText("Ticket wijzigen");
			
			txfTitel.setText(selectedTicket.getTitel());
			cboStatus.setValue(selectedTicket.getStatus());
			txaOmschrijving.setText(selectedTicket.getOmschrijving());
			txaOpmerkingen.setText(selectedTicket.getOpmerkingen());
			cboType.setValue(selectedTicket.getTypeTicket());
			txfKlantId.setText(Integer.toString(selectedTicket.getKlant().getId()));
			cboTechnieker.setValue(selectedTicket.getTechnieker());
			
			if (ac.geefAangemeldeGebruikerType().toString() == "Technieker") {
				cboTechnieker.setDisable(true);
				cboType.setDisable(true);
				lblTitel.setText("Status wijzigen");
				btnKlantAanmaken.setText("Status wijzigen");
				txfTitel.setDisable(true);
				txaOmschrijving.setDisable(true);
				txaOpmerkingen.setDisable(true);
				txfKlantId.setDisable(true);
				btnBestandenToevoegen.setDisable(true);
			}
			
>>>>>>> e44afbb geefTechniekers, wijzig ticket
			
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}	
	}
	// Event Listener on Button[#btnKlantAanmaken].onAction
	@FXML
	public void btnKlantAanmakenOnAction(ActionEvent event) {
		try {
			if (!txfTitel.getText().isBlank()) {
				if (!txaOmschrijving.getText().isBlank()) {
					if (!txaOpmerkingen.getText().isBlank()) {
						if (cboType.getValue() != null) {
							if (!txfKlantId.getText().isBlank()) {
								if (cboTechnieker.getValue() != null) {
									if (cboStatus.getValue() != null) {

									ac.wijzigTicket(selectedTicket.getId(), txfTitel.getText(), TicketStatus.valueOf(cboStatus.getValue().toString()), LocalDate.now(),
											txaOmschrijving.getText(), txaOpmerkingen.getText(),
											Integer.parseInt(cboType.getValue().toString()), Integer.parseInt(txfKlantId.getText()),
<<<<<<< Upstream, based on branch 'main' of https://github.com/HoGentProjectenII/2021-java-g-23.git
											Integer.parseInt(cboTechnieker.getValue().toString()), "Geen bijlage");
									
=======
											cboTechnieker.getValue().getId(), "Geen bijlage");
>>>>>>> e44afbb geefTechniekers, wijzig ticket
									lblFout.setText("Ticket wijzigen gelukt!");
									}else lblFout.setText("Gelieve een status te selecteren.");
								} else
									lblFout.setText("Gelieve een technieker te selecteren.");
							} else
								lblFout.setText("Gelieve een klantId in te voeren.");
						} else
							lblFout.setText("Gelieve een tickettype te selecteren.");
					}
				} else
					lblFout.setText("Gelive een omschrijving in te vullen.");
			} else
				lblFout.setText("Gelieve een titel in te vullen");
		} catch (IllegalArgumentException e) {
			lblFout.setText(e.getMessage());
		}
		}
	
	// Event Listener on Button[#btnBestandenToevoegen].onAction
	@FXML
	public void btnBestandenToevoegen(ActionEvent event) {
		// TODO Autogenerated
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cboTechnieker.setItems(ac.geefTechniekers());
		cboStatus.setItems(FXCollections.observableArrayList(TicketStatus.values()));
		cboType.setItems(FXCollections.observableArrayList(new Integer[] {1, 2, 3}));	
		btnKlantAanmaken.setText("Ticket wijzigen");
		lblTitel.setText("Ticket wijzigen");
		
		txfTitel.setText(selectedTicket.getTitel());
		cboStatus.setValue(selectedTicket.getStatus());
		txaOmschrijving.setText(selectedTicket.getOmschrijving());
		txaOpmerkingen.setText(selectedTicket.getOpmerkingen());
		cboType.setValue(selectedTicket.getTypeTicket());
		txfKlantId.setText(Integer.toString(selectedTicket.getKlant().getId()));
		//cboTechnieker.setValue();
		
	}
}
