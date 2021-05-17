package gui;

import java.io.IOException;

import domein.models.KnowledgebaseGegevens;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class KnowledgebaseItemOmschrijvingSchermController extends GridPane{
	
	@FXML
	private Label lblDatumAangemaakt;
	@FXML
	private Label lblTitel;
	@FXML
	private Label lblOmschrijving;



	public KnowledgebaseItemOmschrijvingSchermController(
			KnowledgebaseBeherenSchermController knowledgebaseBeherenSchermController,
			KnowledgebaseGegevens selectedItem) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("KnowledgebaseItemOmschrijvingScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
			
			lblDatumAangemaakt.setText(selectedItem.getDatumToevoegen().toString());
			lblTitel.setText(selectedItem.getTitel());
			lblOmschrijving.setText(selectedItem.getOmschrijving());
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}	}

}
