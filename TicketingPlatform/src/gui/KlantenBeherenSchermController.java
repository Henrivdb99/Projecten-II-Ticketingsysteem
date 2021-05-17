package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import domein.controllers.AangemeldeGebruikerController;
import domein.models.GebruikerGegevens;
import domein.models.GebruikerStatus;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
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
	private Button btnResetFilters;
	@FXML
	private TextField txfGebruikersnaam;
	@FXML
	private TextField txfNaamVoornaam;
	@FXML
	private TextField txfBedrijf;
	@FXML
	private ChoiceBox<String> cboStatus;
	
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
	@FXML
	private TableColumn<GebruikerGegevens, String> colBedrijf;


	private DashboardSchermController parent;
	private AangemeldeGebruikerController ac;
	private GebruikerGegevens selectedUser;

	private final String alleStatussen = "ALLE";

	public KlantenBeherenSchermController(DashboardSchermController dashboardSchermController, AangemeldeGebruikerController ac) {
		this.parent = dashboardSchermController;
		this.ac= ac;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("KlantenBeherenScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
			
			initializeFilter();
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
		try {
			this.selectedUser = tblView.getSelectionModel().getSelectedItem();
			KlantWijzigenSchermController kwsc = new KlantWijzigenSchermController(this, this.selectedUser, this.ac);
			this.setRight(kwsc);	
		} catch (NullPointerException np) {
			System.out.println(np.getMessage());
		}
	}

	// Event Listener on Button[#btnTerug].onAction
	@FXML
	public void btnTerugOnAction(ActionEvent event) {
		Stage stage = (Stage) (getScene().getWindow());
		stage.setScene(this.parent.getScene());
	}

	// Event Listener on Button[#btnKlantDetails].onAction
	@FXML
	public void btnKlantDetailsOnAction(ActionEvent event) {
		try {
			this.selectedUser = tblView.getSelectionModel().getSelectedItem();
			KlantDetailsSchermController klantDetailsSchermController = new KlantDetailsSchermController(this, this.selectedUser, ac);
			this.setRight(klantDetailsSchermController);
			
		} catch(NullPointerException np) {
			System.out.println(np.getMessage());
		}
	}
	// Event Listener on Button[#btnResetFilters].onAction
	@FXML
	public void btnResetFiltersOnAction(ActionEvent event) {
		txfGebruikersnaam.clear();
		txfNaamVoornaam.clear();
		txfBedrijf.clear();
		cboStatus.setValue(alleStatussen);
		
		ac.changeFilterKlant(alleStatussen, "Status");
	}

	@FXML
	public void filterGebruikersnaam(KeyEvent event) {
		String newValue = txfGebruikersnaam.getText();
		ac.changeFilterKlant(newValue, "Gebruikersnaam");
		
    	//andere filtervakken leegmaken
		txfNaamVoornaam.clear();
		txfBedrijf.clear();
		cboStatus.setValue(alleStatussen);
	}

	// Event Listener on TextField[#txfNaamVoornaam].onKeyReleased
	@FXML
	public void filterNaamVoornaam(KeyEvent event) {
		String newValue = txfNaamVoornaam.getText();
		ac.changeFilterKlant(newValue, "NaamEnVoornaam");
		
    	//andere filtervakken leegmaken
    	txfGebruikersnaam.clear();
    	txfBedrijf.clear();
		cboStatus.setValue(alleStatussen);
	}

	// Event Listener on TextField[#txfBedrijf].onKeyReleased
	@FXML
	public void filterBedrijf(KeyEvent event) {
		String newValue = txfBedrijf.getText();
		ac.changeFilterKlant(newValue, "Bedrijf");
		
    	//andere filtervakken leegmaken
    	txfGebruikersnaam.clear();
		txfNaamVoornaam.clear();
		cboStatus.setValue(alleStatussen);
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
		colBedrijf.setCellValueFactory(new PropertyValueFactory<GebruikerGegevens, String>("bedrijfsnaam"));
		tblView.setItems(ac.geefKlanten());
        
		btnKlantDetails.setDisable(true);
		btnKlantWijzigen.setDisable(true);
	}
	
	private void initializeFilter() {
		
        List<String> statusList = Arrays.stream(GebruikerStatus.values()).map(Enum::name).collect(Collectors.toList());
        statusList.add(alleStatussen);
        cboStatus.setItems(FXCollections.observableArrayList(statusList));
		cboStatus.setValue(GebruikerStatus.ACTIEF.toString());
		
        cboStatus.setOnAction(event -> {
        	txfGebruikersnaam.clear();
    		txfNaamVoornaam.clear();
    		txfBedrijf.clear();
        	
            ac.changeFilterKlant(cboStatus.getValue(), "Status");

        });
        
		ac.changeFilterKlant(cboStatus.getValue(), "Status");
	}
	

}
