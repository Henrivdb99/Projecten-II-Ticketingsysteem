package domein.controllers;

import domein.models.TypeGebruiker;
import domein.models.*;

public abstract class AangemeldeGebruikerController {
	
	public AangemeldeGebruikerController() {
		
	}
	
	public abstract TypeGebruiker geefAangemeldeGebruikerType();

	public Gebruiker geefGebruiker(int id) {
		throw new UnsupportedOperationException();
	}

	public String[] geefGebruikers() {
		throw new UnsupportedOperationException();
	}

	public void voegGebruikerToe(String[] gegevens) {
		throw new UnsupportedOperationException();
	}

	public void updateGebruiker(int id, String[] gegevens) {
		throw new UnsupportedOperationException();
	}

	public void verwijderGebruiker(int id) {
		throw new UnsupportedOperationException();
	}
	
	
}
