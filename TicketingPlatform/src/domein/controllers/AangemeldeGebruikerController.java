package domein.controllers;

import java.time.LocalDate;

import domein.models.Actemium;
import domein.models.Gebruiker;
import domein.models.GebruikerGegevens;
import domein.models.GebruikerStatus;
import domein.models.TicketGegevens;
import domein.models.TicketStatus;
import domein.models.TypeGebruiker;
import javafx.collections.ObservableList;
import persistentie.GebruikerDaoJPA;

public abstract class AangemeldeGebruikerController {
	
	protected Actemium actemium;
	
	public AangemeldeGebruikerController() {
		this(new Actemium());
	}

	public AangemeldeGebruikerController(Actemium actemium) {
		this.actemium = actemium;
	}

	public Actemium getActemium() {
		return actemium;
	}

	public void setActemium(Actemium actemium) {
		this.actemium = actemium;
	}



	public abstract TypeGebruiker geefAangemeldeGebruikerType();

	public Gebruiker geefGebruiker(int id) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}

	public ObservableList<GebruikerGegevens> geefWerknemers() {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}
	public ObservableList<GebruikerGegevens> geefTechniekers() {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}
	
	public ObservableList<GebruikerGegevens> geefKlanten() {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}

	public void verwijderGebruiker(int id) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}

	public void wijzigWerknemer(int id, String naam, String voornaam, String email, String[] telefoonnummers, TypeGebruiker rol, GebruikerStatus status ,String wachtwoord, String[] adres) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}
	
	public void voegWerknemerToe(String naam, String voornaam, String email, String[] telefoonnummers, TypeGebruiker rol, String wachtwoord, String[] adres) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}
	
	public void wijzigKlant(int id, String naam, String voornaam, String email, String[] telefoonnummers, GebruikerStatus status ,String wachtwoord, String[] adres, String bedrijfsnaam) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}
	
	public void voegKlantToe(String naam, String voornaam, String email, String[] telefoonnummers, String wachtwoord, String[] adres, String bedrijfsnaam) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");	
	}

	public void voegTicketToe(String titel, TicketStatus ticketStatus, LocalDate date, String omschrijving,String opmerkingen, int typeTicket, int klantId, int techniekerId, String bijlage) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");	
	}
	public void wijzigTicket(String titel, TicketStatus ticketStatus, LocalDate date, String omschrijving,String opmerkingen, int typeTicket, int klantId, int techniekerId, String bijlage) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");	
	}
	public ObservableList<TicketGegevens> geefTickets() {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}
	public void changeFilter(String newValue, String veld)
	{
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}

	


	
	
	
}
