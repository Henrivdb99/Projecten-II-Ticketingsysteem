package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import domein.controllers.AangemeldeGebruikerController;
import domein.models.KnowledgeBaseGegevens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class KnowledgebaseBeherenSchermController extends BorderPane implements Initializable {
	@FXML
	private Button btnItemToevoegen;
	@FXML
	private Button btnItemWijzigen;
	@FXML
	private Button btnTerug;
	@FXML
	private Button btnToonOmschrijving;
	@FXML
	private TextField txfFilterTitel;
	@FXML
	private TableView<KnowledgeBaseGegevens> tblView;
	@FXML
	private TableColumn<KnowledgeBaseGegevens, Integer> colItemId;
	@FXML
	private TableColumn<KnowledgeBaseGegevens, String> colItemTitel;
	@FXML
	private TableColumn<KnowledgeBaseGegevens, LocalDate> colItemDatum;

	private DashboardSchermController parent;
	private AangemeldeGebruikerController ac;
	private KnowledgeBaseGegevens selectedItem;

	public KnowledgebaseBeherenSchermController(DashboardSchermController dashboardSchermController,
			AangemeldeGebruikerController ac) {
		this.parent = dashboardSchermController;
		this.ac = ac;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("KnowledgebaseBeherenScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();

			if (ac.geefAangemeldeGebruikerType().toString() == "Technieker") {
				btnItemToevoegen.setVisible(false);
				btnItemWijzigen.setVisible(false);
			}

			btnToonOmschrijving.setDisable(true);
			btnItemWijzigen.setDisable(true);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@FXML
	public void btnItemToevoegenOnAction(ActionEvent event) {
		KnowledgebaseItemToevoegenSchermController ktvsc = new KnowledgebaseItemToevoegenSchermController(this, ac);
		this.setRight(ktvsc);
	}

	@FXML
	public void btnItemWijzigenOnAction(ActionEvent event) {
		this.selectedItem = tblView.getSelectionModel().getSelectedItem();
		KnowledgebaseItemWijzigenSchermController ktwsc = new KnowledgebaseItemWijzigenSchermController(this,
				selectedItem, ac);
		this.setRight(ktwsc);
	}

	@FXML
	public void btnTerugOnAction(ActionEvent event) {
		Stage stage = (Stage) (getScene().getWindow());
		stage.setScene(this.parent.getScene());
	}

	@FXML
	public void btnToonOmschrijvingOnAction(ActionEvent event) {
		this.selectedItem = tblView.getSelectionModel().getSelectedItem();
		KnowledgebaseItemOmschrijvingSchermController tdsc = new KnowledgebaseItemOmschrijvingSchermController(this,
				this.selectedItem);

		this.setRight(tdsc);
	}

	@FXML
	public void filterTitel(KeyEvent event) {
		String newValue = txfFilterTitel.getText();
		ac.changeFilterKnowledgebase(newValue, "knowledgebaseTitel");
		tblView.setPlaceholder(new Label("Geen gegevens gevonden met zoekterm " + newValue));
	}

	@FXML
	public void userClickedOnTable(MouseEvent event) {
		if(tblView.getSelectionModel().getSelectedItem() != null) 
		{
			btnItemWijzigen.setDisable(false);
			btnToonOmschrijving.setDisable(false);
			setRight(null);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colItemId.setCellValueFactory(new PropertyValueFactory<KnowledgeBaseGegevens, Integer>("knowledgeBaseId"));
		colItemDatum.setCellValueFactory(new PropertyValueFactory<KnowledgeBaseGegevens, LocalDate>("datumToevoegen"));
		colItemTitel.setCellValueFactory(new PropertyValueFactory<KnowledgeBaseGegevens, String>("titel"));
		tblView.setItems(ac.geefKnowledgebaseItems());
		ac.geefKnowledgebaseItems().comparatorProperty().bind(tblView.comparatorProperty());
	}
}
