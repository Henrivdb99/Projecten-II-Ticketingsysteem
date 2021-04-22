package prullenbak;

import java.io.IOException;

import domein.controllers.LoginController;
import gui.DashboardSchermGebruikerController;
import gui.LoginSchermController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DashboardSchermAdministratorController extends DashboardSchermGebruikerController{
	
	@FXML
	private Button btnAfmelden;
	@FXML
	private Button btnKlantenBeheren;
	@FXML
	private Button btnWerknemersBeheren;

	public DashboardSchermAdministratorController(LoginSchermController loginSchermController,
			LoginController gebruikerController) {
		super(loginSchermController, gebruikerController);
		btnKlantenBeheren.setDisable(false);
		btnWerknemersBeheren.setDisable(false);
		btnKlantenBeheren.setVisible(true);
		btnWerknemersBeheren.setVisible(true);
		// TODO Auto-generated constructor stub
	}

	// Event Listener on Button[#btnKlantenBeheren].onAction
	@FXML
	public void btnKlantenBeherenOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnWerknemersBeheren].onAction
	@FXML
	public void btnWerknemersBeherenOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
}
