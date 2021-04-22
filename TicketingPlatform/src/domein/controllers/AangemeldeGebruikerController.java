package domein.controllers;

import domein.models.Gebruiker;
import domein.models.TypeGebruiker;
import persistentie.GebruikerDaoJPA;

public abstract class AangemeldeGebruikerController {
	
	protected GebruikerDaoJPA gebruikerRepo;
	
	public AangemeldeGebruikerController() {
		this(new GebruikerDaoJPA());
	}
	
	public AangemeldeGebruikerController(GebruikerDaoJPA gebruikerRepo) {
		this.gebruikerRepo = gebruikerRepo;
	}

	public GebruikerDaoJPA getGebruikerRepo() {
		return gebruikerRepo;
	}

	public void setGebruikerRepo(GebruikerDaoJPA gebruikerRepo) {
		this.gebruikerRepo = gebruikerRepo;
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
