package gui;

import java.io.IOException;

import domein.controllers.AangemeldeGebruikerController;
import domein.controllers.LoginController;
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

public class LoginSchermController extends AnchorPane {
	@FXML
	private Label lblLogIn;
	@FXML
	private Label lblGebruikersnaam;
	@FXML
	private Label lblWachtwoord;
	@FXML
	private PasswordField txfWachtwoord;
	@FXML
	private TextField txfGebruikersnaam;
	@FXML
	private Button btnInloggen;
	@FXML
	private Label lblFout;
	
	protected LoginController lg;

	public LoginSchermController(LoginController gebruikerController) {
		super();
		this.lg = gebruikerController;				
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();

			setPlaceholders();

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public void btnInloggenOnAction(ActionEvent event) throws IOException {
		String username = txfGebruikersnaam.getText();
		String wachtwoord = txfWachtwoord.getText();

		try {
			lg.meldAan(username, wachtwoord);
			AangemeldeGebruikerController ac = lg.geefJuisteController();
			
			DashboardSchermController child = new DashboardSchermController(this, ac);
			
			lblFout.setText("");
			
			Scene scene = new Scene(child);
			Stage stage = (Stage) this.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
			

		} catch (IllegalArgumentException e) {		
			System.out.println(e.getMessage());
			txfGebruikersnaam.selectAll();
			txfGebruikersnaam.requestFocus();
			this.lblFout.setText(e.getMessage());
		}

	}
	
	private void setPlaceholders() {
		this.txfGebruikersnaam.setText("administrator@gmail.com");
		this.txfWachtwoord.setText("wachtwoord1");
	}

}
