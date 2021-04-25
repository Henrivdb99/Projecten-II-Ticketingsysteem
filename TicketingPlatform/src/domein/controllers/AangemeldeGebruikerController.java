package domein.controllers;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.models.Gebruiker;
import domein.models.TypeGebruiker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}

	public ObservableList<Gebruiker> geefWerknemers() {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}
	
	public ObservableList<Gebruiker> geefKlanten() {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}

	public void voegGebruikerToe(String[] gegevens) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}

	public void updateGebruiker(int id, String[] gegevens) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}

	public void verwijderGebruiker(int id) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}

	public void voegMedewerkerToe(String naam, String voornaam, String email, String gsmnummer,
			String vasteLijnWerk, String rol, String wachtwoord, String adres) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
		
	}
	
	
	
	
}
