package main;

import controllers.LoginController;
import controllers.PopuleerDB;

public class StartUp {

	public static void main(String[] args) {
		System.out.println("Hello world");
		
		LoginController gc = new LoginController();
		
		PopuleerDB.run();

		System.out.println("Goodbye world");
	}

}
