package main;

import controllers.GebruikerController;
import controllers.PopuleerDB;
import gui.LoginSchermController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartGui extends Application {

	@Override
	public void start(Stage stage) throws Exception {	
		GebruikerController gc  = new GebruikerController();		
		PopuleerDB.run();
		Scene scene = new Scene(new LoginSchermController(gc));
		stage.setScene(scene);
		stage.setTitle("Actemium");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
