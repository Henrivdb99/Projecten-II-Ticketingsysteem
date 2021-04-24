package prullenbak;

import domein.controllers.LoginController;
import gui.DashboardSchermController;
import gui.LoginSchermController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import gui.*;
import javafx.scene.control.*;
import domein.controllers.*;
import controllers.*;

public class DashboardSchermTechniekerController extends DashboardSchermController {
	@FXML
	private Button btnOpenstaandeTickets;
	@FXML
	private Button btnAfgehandeldeTickets;
	@FXML
	private Button btnStatistieken;
	@FXML
	private Button btnKnowledgeBaseBeheren;
	
	
	public DashboardSchermTechniekerController(LoginSchermController loginSchermController, LoginController gebruikerController) {
		super(loginSchermController, gebruikerController);
		btnOpenstaandeTickets.setDisable(false);
		btnAfgehandeldeTickets.setDisable(false);
		btnStatistieken.setDisable(false);
		btnKnowledgeBaseBeheren.setDisable(false);
		btnOpenstaandeTickets.setVisible(true);
		btnAfgehandeldeTickets.setVisible(true);
		btnStatistieken.setVisible(true);
		btnKnowledgeBaseBeheren.setVisible(true);
	}


	public DashboardSchermTechniekerController(LoginSchermController loginSchermController,
			LoginController gebruikerController) {
		throw new UnsupportedOperationException();
	}


	public DashboardSchermTechniekerController(LoginSchermController loginSchermController,
			LoginController gebruikerController) {
		throw new UnsupportedOperationException();
	}
	
	

}
