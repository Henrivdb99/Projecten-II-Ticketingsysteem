package main;

import controllers.LoginController;
import controllers.PopuleerDB;
import gui.LoginSchermController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartGui extends Application {

	@Override
	public void start(Stage stage) throws Exception {	
		LoginController gc  = new LoginController();		
		PopuleerDB.run();
		Scene scene = new Scene(new LoginSchermController(gc));
		stage.setScene(scene);
		stage.setTitle("Ticketing Platform");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
