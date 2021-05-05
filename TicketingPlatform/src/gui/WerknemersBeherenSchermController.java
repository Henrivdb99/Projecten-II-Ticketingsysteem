package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.controllers.AangemeldeGebruikerController;
import domein.models.GebruikerGegevens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class WerknemersBeherenSchermController extends BorderPane implements Initializable {
	@FXML
	private Button btnWerknemerToevoegen;
	@FXML
	private Button btnWerknemerWijzigen;
	@FXML
	private Button btnTerug;
	@FXML
	private Button btnWerknemerDetails;

	@FXML
	private TableView<GebruikerGegevens> tblView;
	@FXML
	private TableColumn<GebruikerGegevens, String> colGebruikersnaam;
	@FXML
	private TableColumn<GebruikerGegevens, String> colNaam;
	@FXML
	private TableColumn<GebruikerGegevens, String> colVoornaam;
	@FXML
	private TableColumn<GebruikerGegevens, String> colRol;
	@FXML
	private TableColumn<GebruikerGegevens, String> colStatus;

	private DashboardSchermController parent;
	private AangemeldeGebruikerController ac;
	private GebruikerGegevens selectedUser;

	public WerknemersBeherenSchermController(DashboardSchermController dashboardSchermController,
			AangemeldeGebruikerController ac) {
		this.parent = dashboardSchermController;
		this.ac = ac;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("WerknemersBeherenScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();

			btnWerknemerDetails.setDisable(true);
			btnWerknemerWijzigen.setDisable(true);

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	// Event Listener on Button[#btnWerknemerToevoegen].onAction
	@FXML
	public void btnWerknemerToevoegenOnAction(ActionEvent event) {
		WerknemerToevoegenSchermController wtsc = new WerknemerToevoegenSchermController(this, ac);
		this.setRight(wtsc);
	}

	// Event Listener on Button[#btnWerknemerWijzigen].onAction
	@FXML
	public void btnWerknemerWijzigenOnAction(ActionEvent event) {
		this.selectedUser = tblView.getSelectionModel().getSelectedItem();
		WerknemerWijzigenSchermController wwsc = new WerknemerWijzigenSchermController(this, this.selectedUser, this.ac);
		this.setRight(wwsc);
	}

	// Event Listener on Button[#btnTerug].onAction
	@FXML
	public void btnTerugOnAction(ActionEvent event) {
		Stage stage = (Stage) (getScene().getWindow());
		stage.setScene(this.parent.getScene());
	}

	// Event Listener on Button[#btnWerknemerDetails].onAction
	@FXML
	public void btnWerknemerDetailsOnAction(ActionEvent event) {
		this.selectedUser = tblView.getSelectionModel().getSelectedItem();
		WerknemerDetailsSchermController werknemerDetailsSchermController = new WerknemerDetailsSchermController(this,
				this.selectedUser);

		this.setRight(werknemerDetailsSchermController);
	}

	// Event Listener on TableView[#tblView].onMouseClicked
	@FXML
	public void userClickedOnTable(MouseEvent event) {
		btnWerknemerDetails.setDisable(false);
		btnWerknemerWijzigen.setDisable(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colGebruikersnaam.setCellValueFactory(new PropertyValueFactory<GebruikerGegevens, String>("emailAdres"));
		colNaam.setCellValueFactory(new PropertyValueFactory<GebruikerGegevens, String>("naam"));
		colVoornaam.setCellValueFactory(new PropertyValueFactory<GebruikerGegevens, String>("voornaam"));
		colRol.setCellValueFactory(new PropertyValueFactory<GebruikerGegevens, String>("rol"));
		colStatus.setCellValueFactory(new PropertyValueFactory<GebruikerGegevens, String>("status"));
		tblView.setItems(ac.geefWerknemers());

	}
}
