package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import domein.controllers.AangemeldeGebruikerController;
import domein.models.ContractGegevens;
import domein.models.GebruikerGegevens;
import domein.models.GebruikerStatus;
import domein.models.Klant;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class KlantenContractenSchermController extends GridPane implements Initializable {
	
	@FXML
	private Button btnTerug;
	
	@FXML
	private TableView<ContractGegevens> tblView;
	@FXML
	private TableColumn<ContractGegevens, String> colContractNummer;
	@FXML
	private TableColumn<ContractGegevens, String> colContractType;
	@FXML
	private TableColumn<ContractGegevens, String> colContractStatus;
	@FXML
	private TableColumn<ContractGegevens, String> colContractStart;
	@FXML
	private TableColumn<ContractGegevens, String> colContractEind;
	@FXML
	private Label lblKlantNaam;

	private KlantenBeherenSchermController parent;
	private AangemeldeGebruikerController ac;
	private GebruikerGegevens selectedUser;

	public KlantenContractenSchermController(KlantenBeherenSchermController klantDetailsSchermController, GebruikerGegevens selectedUser, AangemeldeGebruikerController ac) {
		this.parent = klantDetailsSchermController;
		this.selectedUser = selectedUser;
		this.ac= ac;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("KlantenContractenScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
			
			lblKlantNaam.setText(selectedUser.getVoornaam() + " " + selectedUser.getNaam());
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colContractNummer.setCellValueFactory(new PropertyValueFactory<ContractGegevens, String>("contractId"));
		colContractType.setCellValueFactory(new PropertyValueFactory<ContractGegevens, String>("ContractTypeNaam"));
		colContractStatus.setCellValueFactory(new PropertyValueFactory<ContractGegevens, String>("contractStatus"));
		colContractStart.setCellValueFactory(new PropertyValueFactory<ContractGegevens, String>("startDatum"));
		colContractEind.setCellValueFactory(new PropertyValueFactory<ContractGegevens, String>("eindDatum"));
		tblView.setItems(ac.geefContracten(selectedUser.getId()));
		
	}

}
