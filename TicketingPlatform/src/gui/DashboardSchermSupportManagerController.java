package gui;

import controllers.GebruikerController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DashboardSchermSupportManagerController extends DashboardSchermGebruikerController {
	@FXML
	private Button btnOpenstaandeTickets;
	@FXML
	private Button btnAfgehandeldeTickets;
	@FXML
	private Button btnStatistieken;
	@FXML
	private Button btnKnowledgeBaseBeheren;
	@FXML
	private Button btnNieuwTicketAanmaken;
	
	
	public DashboardSchermSupportManagerController(LoginSchermController loginSchermController,
			GebruikerController gebruikerController) {
		super(loginSchermController, gebruikerController);
		// TODO Auto-generated constructor stub
		
	}

}
