package main;

import domein.PopuleerDB;
import domein.controllers.LoginController;

public class StartUp {

	public static void main(String[] args) {
		System.out.println("Hello world");
		
		LoginController gc = new LoginController();
		
		PopuleerDB.run();

		System.out.println("Goodbye world");
	}

}
