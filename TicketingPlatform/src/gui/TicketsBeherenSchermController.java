package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.controllers.AangemeldeGebruikerController;
import domein.models.Gebruiker;
import domein.models.Ticket;
import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class TicketsBeherenSchermController extends BorderPane implements Initializable {
	@FXML
	private Button btnTicketToevoegen;
	@FXML
	private Button btnTicketWijzigen;
	@FXML
	private Button btnTerug;
	@FXML
	private Button btnTicketDetails;
	@FXML
	private TableView tblView;
	@FXML
	private TableColumn colTicketId;
	@FXML
	private TableColumn colTicketTitel;
	@FXML
	private TableColumn colTicketType;
	@FXML
	private TableColumn colKlant;
	@FXML
	private TableColumn colStatus;

	private DashboardSchermController parent;
	private AangemeldeGebruikerController ac;
	private Ticket selectedTicket;

	public TicketsBeherenSchermController(DashboardSchermController dashboardSchermController,
			AangemeldeGebruikerController ac) {
		this.parent = dashboardSchermController;
		this.ac = ac;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TicketsBeherenScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();

			btnTicketDetails.setDisable(true);
			btnTicketWijzigen.setDisable(true);

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	// Event Listener on Button[#btnTicketToevoegen].onAction
	@FXML
	public void btnTicketToevoegenOnAction(ActionEvent event) {
		TicketToevoegenSchermController ttsc = new TicketToevoegenSchermController(this, ac);
		this.setRight(ttsc);
	}

	// Event Listener on Button[#btnTicketWijzigen].onAction
	@FXML
	public void btnTicketWijzigenOnAction(ActionEvent event) {
		this.selectedTicket = tblView.getSelectionModel().getSelectedItem();
		TicketWijzigenSchermController twsc = new TicketWijzigenSchermController(this, selectedTicket, ac);
		this.setRight(twsc);
	}

	// Event Listener on Button[#btnTerug].onAction
	@FXML
	public void btnTerugOnAction(ActionEvent event) {
		Stage stage = (Stage) (getScene().getWindow());
		stage.setScene(this.parent.getScene());
	}

	// Event Listener on Button[#btnTicketDetails].onAction
	@FXML
	public void btnTicketDetailsOnAction(ActionEvent event) {
		this.selectedTicket = tblView.getSelectionModel().getSelectedItem();
		TicketDetailsSchermController tdsc = new TicketDetailsSchermController(this, this.selectedTicket);

		this.setRight(tdsc);
	}

	// Event Listener on TableView[#tblView].onMouseClicked
	@FXML
	public void userClickedOnTable(MouseEvent event) {
		btnTicketDetails.setDisable(false);
		btnTicketWijzigen.setDisable(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colTicketId.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("ticketId"));
		colTicketType.setCellValueFactory(new PropertyValueFactory<Ticket, String>("type"));
		colTicketTitel.setCellValueFactory(new PropertyValueFactory<Ticket, String>("titel"));
		colKlant.setCellValueFactory(new PropertyValueFactory<Klant, String>("naamVoornaam"));
		colStatus.setCellValueFactory(new PropertyValueFactory<Ticket, String>("status"));
		tblView.setItems(ac.geefWerknemers());
	}
}
