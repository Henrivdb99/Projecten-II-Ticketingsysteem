package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginSchermController {
	@FXML
	private TextField txfGebruikersnaam;
	@FXML
	private PasswordField txfWachtwoord;
	@FXML
	private Button btnInloggen;
	@FXML
	private Label lblFout, lblWachtwoord, lblGebruikersnaam, lblLogin;
	
	private DomeinController dc = StartGui.dc;
	public LoginSchermController() {
		
	}
	
	public void btnInloggenOnAction(ActionEvent event) throws IOException {
		String username = txfGebruikersnaam.getText();
		String wachtwoord = txfWachtwoord.getText();		
		
	     
	     try {
	    	dc.meldAan(username, wachtwoord);
	    	Parent logIn = FXMLLoader.load(getClass().getResource("DashboardSchermAdministrator.fxml"));
	 		Scene logInScene = new Scene(logIn);
	 		
	 		Stage venster = (Stage)((Node)event.getSource()).getScene().getWindow();
	 		venster.setScene(logInScene);
	 		venster.show();	 
	 		
		 
	     } catch(IllegalArgumentException e) {
	    	 this.lblFout.setText(e.getMessage());
	     }
	    
	 }

	}
