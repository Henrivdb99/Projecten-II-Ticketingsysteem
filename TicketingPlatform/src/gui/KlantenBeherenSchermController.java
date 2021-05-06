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

public class KlantenBeherenSchermController extends BorderPane implements Initializable{
	@FXML
	private Button btnKlantToevoegen;
	@FXML
	private Button btnKlantWijzigen;
	@FXML
	private Button btnTerug;
	@FXML
	private Button btnKlantDetails;
	
	@FXML
	private TableView<GebruikerGegevens> tblView;
	@FXML
	private TableColumn<GebruikerGegevens, String> colGebruikersnaam;
	@FXML
	private TableColumn<GebruikerGegevens, String> colNaam;
	@FXML
	private TableColumn<GebruikerGegevens, String> colVoornaam;
	@FXML
	private TableColumn<GebruikerGegevens, String> colStatus;

	private DashboardSchermController parent;
	private AangemeldeGebruikerController ac;
	private GebruikerGegevens selectedUser;

	public KlantenBeherenSchermController(DashboardSchermController dashboardSchermController, AangemeldeGebruikerController ac) {
		this.parent = dashboardSchermController;
		this.ac= ac;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("KlantenBeherenScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
			
			btnKlantDetails.setDisable(true);
			btnKlantWijzigen.setDisable(true);


		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	// Event Listener on Button[#btnKlantToevoegen].onAction
	@FXML
	public void btnKlantToevoegenOnAction(ActionEvent event) {
		KlantToevoegenSchermController ktsc = new KlantToevoegenSchermController(this, ac);
		this.setRight(ktsc);
	}

	// Event Listener on Button[#btnKlantWijzigen].onAction
	@FXML
	public void btnKlantWijzigenOnAction(ActionEvent event) {
		this.selectedUser = tblView.getSelectionModel().getSelectedItem();
		KlantWijzigenSchermController kwsc = new KlantWijzigenSchermController(this, this.selectedUser, this.ac);
		this.setRight(kwsc);	}

	// Event Listener on Button[#btnTerug].onAction
	@FXML
	public void btnTerugOnAction(ActionEvent event) {
		Stage stage = (Stage) (getScene().getWindow());
		stage.setScene(this.parent.getScene());
	}

	// Event Listener on Button[#btnKlantDetails].onAction
	@FXML
	public void btnKlantDetailsOnAction(ActionEvent event) {
		this.selectedUser= tblView.getSelectionModel().getSelectedItem();
		KlantDetailsSchermController klantDetailsSchermController = new KlantDetailsSchermController(this, this.selectedUser);

		this.setRight(klantDetailsSchermController);
	}
	// Event Listener on TableView[#tblView].onMouseClicked
	@FXML
	public void userClickedOnTable(MouseEvent event) {
        btnKlantDetails.setDisable(false);
        btnKlantWijzigen.setDisable(false);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colGebruikersnaam.setCellValueFactory(new PropertyValueFactory<GebruikerGegevens, String>("emailAdres"));
		colNaam.setCellValueFactory(new PropertyValueFactory<GebruikerGegevens, String>("naam"));
		colVoornaam.setCellValueFactory(new PropertyValueFactory<GebruikerGegevens, String>("voornaam"));
		colStatus.setCellValueFactory(new PropertyValueFactory<GebruikerGegevens, String>("status"));
        tblView.setItems(ac.geefKlanten());
        
	}
}
