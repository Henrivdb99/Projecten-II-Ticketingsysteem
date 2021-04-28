package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.controllers.AangemeldeGebruikerController;
import domein.controllers.AdministratorController;
import domein.models.Administrator;
import domein.models.Gebruiker;
import domein.models.GebruikerStatus;
import domein.models.SupportManager;
import domein.models.Technieker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

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
	private TableView<Gebruiker> tblView;
	@FXML
	private TableColumn<Gebruiker, String> colGebruikersnaam;
	@FXML
	private TableColumn<Gebruiker, String> colNaam;
	@FXML
	private TableColumn<Gebruiker, String> colVoornaam;
	@FXML
	private TableColumn<Gebruiker, String> colRol;
	@FXML
	private TableColumn<Gebruiker, String> colStatus;

	private DashboardSchermController parent;
	private AangemeldeGebruikerController ac;
	private Gebruiker selectedUser;

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

		Scene scene = new Scene(wtsc);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	// Event Listener on Button[#btnWerknemerWijzigen].onAction
	@FXML
	public void btnWerknemerWijzigenOnAction(ActionEvent event) {
		this.selectedUser = tblView.getSelectionModel().getSelectedItem();
		WerknemerWijzigenSchermController wwsc = new WerknemerWijzigenSchermController(this, this.selectedUser, this.ac);

		Scene scene = new Scene(wwsc);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
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

		Scene scene = new Scene(werknemerDetailsSchermController);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	// Event Listener on TableView[#tblView].onMouseClicked
	@FXML
	public void userClickedOnTable(MouseEvent event) {
		btnWerknemerDetails.setDisable(false);
		btnWerknemerWijzigen.setDisable(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colGebruikersnaam.setCellValueFactory(new PropertyValueFactory<Gebruiker, String>("emailAdres"));
		colNaam.setCellValueFactory(new PropertyValueFactory<Gebruiker, String>("naam"));
		colVoornaam.setCellValueFactory(new PropertyValueFactory<Gebruiker, String>("voornaam"));
		colRol.setCellValueFactory(new PropertyValueFactory<Gebruiker, String>("rol"));
		colStatus.setCellValueFactory(new PropertyValueFactory<Gebruiker, String>("status"));
		tblView.setItems(ac.geefWerknemers());

	}
}
