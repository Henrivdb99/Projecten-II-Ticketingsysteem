package main;

import domein.PopuleerDB;
import domein.controllers.LoginController;
import domein.models.WerknemerRol;
import persistentie.GebruikerDaoJPA;

public class StartUp {

	public static void main(String[] args) {
		System.out.println("Hello world");
		
		LoginController gc = new LoginController();
		
		PopuleerDB.run();
		
		new GebruikerDaoJPA().geefWerknemersByRol(WerknemerRol.Technieker).stream().forEach(e -> System.out.println(e));

		System.out.println("Goodbye world");
	}

}
