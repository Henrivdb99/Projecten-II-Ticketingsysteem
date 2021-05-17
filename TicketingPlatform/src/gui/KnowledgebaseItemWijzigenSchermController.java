package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

import domein.controllers.AangemeldeGebruikerController;
import domein.models.KnowledgeBaseGegevens;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

public class KnowledgebaseItemWijzigenSchermController extends GridPane {
	@FXML
	private Label lblTitel;
	@FXML
	private Button btnItemToevoegen;
	@FXML
	private Label lblFout;
	@FXML
	private TextField txfTitel;
	@FXML
	private TextArea txaOmschrijving;

	private AangemeldeGebruikerController ac;

	public KnowledgebaseItemWijzigenSchermController(
			KnowledgebaseBeherenSchermController knowledgebaseBeherenSchermController,
			KnowledgeBaseGegevens selectedItem, AangemeldeGebruikerController ac) {
		try {
			this.ac = ac;

			FXMLLoader loader = new FXMLLoader(getClass().getResource("KnowledgebaseItemToevoegenScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();

			btnItemToevoegen.setText("Item Wijzigen");
			lblTitel.setText("Item Wijzigen");

			txfTitel.setText(selectedItem.getTitel());
			txaOmschrijving.setText(selectedItem.getOmschrijving());

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@FXML
	public void btnItemToevoegenOnAction(ActionEvent event) {
		if (!txfTitel.getText().isBlank()) {
			if (!txaOmschrijving.getText().isBlank()) {
				ac.wijzigKnowledgebaseItem(txfTitel.getText(), txaOmschrijving.getText());
				lblFout.setText("Item wijzigen gelukt!");
			} else
				lblFout.setText("Gelieve een omschrijving in te voeren.");
		} else
			lblFout.setText("Gelieve een titel in te voeren.");
	}
}
