package main;

import domein.GebruikerController;
import domein.PopuleerDB;

public class StartUp {

	public static void main(String[] args) {
		System.out.println("Hello world");
		
		GebruikerController gc = new GebruikerController();
		
		PopuleerDB.run();

		System.out.println("Goodbye world");
	}

}
