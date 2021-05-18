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
import domein.models.TicketStatus;
import domein.models.WerknemerRol;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	private Button btnResetFilters;
	@FXML
	private TextField txfGebruikersnaam;
	@FXML
	private TextField txfNaamVoornaam;
	@FXML
	private TextField txfRol;
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
	private TableColumn<GebruikerGegevens, String> colRol;
	@FXML
	private TableColumn<GebruikerGegevens, String> colStatus;

	private DashboardSchermController parent;
	private AangemeldeGebruikerController ac;
	private GebruikerGegevens selectedUser;
	//filterwaarden:
	private final String alleStatussen = "ALLE";

	public WerknemersBeherenSchermController(DashboardSchermController dashboardSchermController,
			AangemeldeGebruikerController ac) {
		this.parent = dashboardSchermController;
		this.ac = ac;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("WerknemersBeherenScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();

			initializeFilter();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@FXML
	public void btnWerknemerToevoegenOnAction(ActionEvent event) {
		WerknemerToevoegenSchermController wtsc = new WerknemerToevoegenSchermController(this, ac);
		this.setRight(wtsc);
	}

	@FXML
	public void btnWerknemerWijzigenOnAction(ActionEvent event) {
		try {
			this.selectedUser = tblView.getSelectionModel().getSelectedItem();
			WerknemerWijzigenSchermController wwsc = new WerknemerWijzigenSchermController(this, this.selectedUser,
					this.ac);
			this.setRight(wwsc);
		} catch (NullPointerException np) {
			//message?
			System.out.println(np.getMessage());
		}
	}

	@FXML
	public void btnTerugOnAction(ActionEvent event) {
		Stage stage = (Stage) (getScene().getWindow());
		stage.setScene(this.parent.getScene());
	}

	// Event Listener on Button[#btnWerknemerDetails].onAction
	@FXML
	public void btnWerknemerDetailsOnAction(ActionEvent event) {
		// Observerrelatie tussen overzicht en detail, breng domein op de hoogte voor
		// wat je hebt geselecteerd. (domein huidige werknemer bijhouden)
		try {
			this.selectedUser = tblView.getSelectionModel().getSelectedItem();
			WerknemerDetailsSchermController werknemerDetailsSchermController = new WerknemerDetailsSchermController(this,
					this.selectedUser);

			this.setRight(werknemerDetailsSchermController);
		} catch(NullPointerException np) {
			System.out.println(np.getMessage());
			//message?
		}
	}

	// Event Listener on Button[#btnResetFilters].onAction
	@FXML
	public void btnResetFiltersOnAction(ActionEvent event) {
		txfGebruikersnaam.clear();
		txfNaamVoornaam.clear();
		txfRol.clear();
		cboStatus.setValue(alleStatussen);
		
		ac.changeFilterWerknemer(alleStatussen, "Status");
	}

	@FXML
	public void filterGebruikersnaam(KeyEvent event) {
		String newValue = txfGebruikersnaam.getText();
		ac.changeFilterWerknemer(newValue, "Gebruikersnaam");
		
    	//andere filtervakken leegmaken
		txfNaamVoornaam.clear();
		txfRol.clear();
		cboStatus.setValue(alleStatussen);
	}

	// Event Listener on TextField[#txfNaamVoornaam].onKeyReleased
	@FXML
	public void filterNaamVoornaam(KeyEvent event) {
		String newValue = txfNaamVoornaam.getText();
		ac.changeFilterWerknemer(newValue, "NaamEnVoornaam");
		
    	//andere filtervakken leegmaken
    	txfGebruikersnaam.clear();
		txfRol.clear();
		cboStatus.setValue(alleStatussen); //probleem: nu wordt die listener ook getriggered
	}

	// Event Listener on TextField[#txfRol].onKeyReleased
	@FXML
	public void filterRol(KeyEvent event) {
		String newValue = txfRol.getText();
		ac.changeFilterWerknemer(newValue, "Rol");
		
    	//andere filtervakken leegmaken
    	txfGebruikersnaam.clear();
		txfNaamVoornaam.clear();
		cboStatus.setValue(alleStatussen);
	}

	@FXML
	public void userClickedOnTable(MouseEvent event) {
		if(tblView.getSelectionModel().getSelectedItem() != null) {
			btnWerknemerDetails.setDisable(false);
			btnWerknemerWijzigen.setDisable(false);
		}
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colGebruikersnaam.setCellValueFactory(new PropertyValueFactory<GebruikerGegevens, String>("emailAdres"));
		colNaam.setCellValueFactory(new PropertyValueFactory<GebruikerGegevens, String>("naam"));
		colVoornaam.setCellValueFactory(new PropertyValueFactory<GebruikerGegevens, String>("voornaam"));
		colRol.setCellValueFactory(new PropertyValueFactory<GebruikerGegevens, String>("rol"));
		colStatus.setCellValueFactory(new PropertyValueFactory<GebruikerGegevens, String>("status"));
		tblView.setItems(ac.geefWerknemers());
		ac.geefWerknemers().comparatorProperty().bind(tblView.comparatorProperty());
		btnWerknemerDetails.setDisable(true);
		btnWerknemerWijzigen.setDisable(true);
	}
	
	private void initializeFilter() {
		
        List<String> statusList = Arrays.stream(GebruikerStatus.values()).map(Enum::name).collect(Collectors.toList());
        statusList.add(alleStatussen);
        cboStatus.setItems(FXCollections.observableArrayList(statusList));
		cboStatus.setValue(GebruikerStatus.ACTIEF.toString());
		
        cboStatus.setOnAction(event -> {
        	//andere filtervakken leegmaken
        	txfGebruikersnaam.clear();
    		txfNaamVoornaam.clear();
    		txfRol.clear();
        	
            ac.changeFilterWerknemer(cboStatus.getValue(), "Status");

        });
        
		ac.changeFilterWerknemer(cboStatus.getValue(), "Status"); //default filter
	}
	
}
