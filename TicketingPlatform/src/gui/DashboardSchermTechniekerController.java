package gui;

import controllers.GebruikerController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DashboardSchermTechniekerController extends DashboardSchermGebruikerController {
	@FXML
	private Button btnOpenstaandeTickets;
	@FXML
	private Button btnAfgehandeldeTickets;
	@FXML
	private Button btnStatistieken;
	@FXML
	private Button btnKnowledgeBaseBeheren;
	
	
	public DashboardSchermTechniekerController(LoginSchermController loginSchermController, GebruikerController gebruikerController) {
		super(loginSchermController, gebruikerController);
	}
	
	

}
