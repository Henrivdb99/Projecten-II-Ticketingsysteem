package gui;

import java.io.IOException;

import controllers.GebruikerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import domein.*;
import javafx.event.*;

public class LoginSchermController extends AnchorPane {
	@FXML
	private TextField txfGebruikersnaam;
	@FXML
	private PasswordField txfWachtwoord;
	@FXML
	private Button btnInloggen;
	@FXML
	private Label lblFout, lblWachtwoord, lblGebruikersnaam, lblLogin;

	private GebruikerController gebruikerController;

	public LoginSchermController(GebruikerController gc) {
		this.gebruikerController = gc;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScherm.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public void btnInloggenOnAction(ActionEvent event) throws IOException {
		String username = txfGebruikersnaam.getText();
		String wachtwoord = txfWachtwoord.getText();

		try {
			//gebruikerController.meldAan(username, wachtwoord);
			DashboardSchermAdministratorController dsac = new DashboardSchermAdministratorController(this);
			Scene scene = new Scene(dsac);
			Stage stage = (Stage)this.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
			

		} catch (IllegalArgumentException e) {
			this.lblFout.setText(e.getMessage());
		}

	}

}
