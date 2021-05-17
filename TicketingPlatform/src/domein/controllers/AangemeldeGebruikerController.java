package domein.controllers;

import java.time.LocalDate;

import domein.models.Actemium;
import domein.models.Gebruiker;
import domein.models.GebruikerGegevens;
import domein.models.GebruikerStatus;
import domein.models.Ticket;
import domein.models.TicketGegevens;
import domein.models.TicketStatus;
import domein.models.TypeGebruiker;
import domein.models.Werknemer;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

public abstract class AangemeldeGebruikerController {
	
	protected Actemium actemium;
	
	private Werknemer aangemeldeGebruiker;

	public AangemeldeGebruikerController(Werknemer aangemeldeGebruiker) {
		this(new Actemium(), aangemeldeGebruiker);
	}

	public AangemeldeGebruikerController(Actemium actemium, Werknemer aangemeldeGebruiker) {
		this.actemium = actemium;
		this.aangemeldeGebruiker = aangemeldeGebruiker;
	}
	
	// === getters en setters ===

	public Actemium getActemium() {
		return actemium;
	}

	public void setActemium(Actemium actemium) {
		this.actemium = actemium;
	}

	public Werknemer getAangemeldeGebruiker() {
		return this.aangemeldeGebruiker;
	}
	
	public void setAangemeldeGebruiker(Werknemer aangemeldeGebruiker) {
		this.aangemeldeGebruiker = aangemeldeGebruiker;
	}
	
	// === Algemene methodes ===
	
	public void changeFilter(String filterValue, String veld) {
		actemium.changeFilter(filterValue, veld);
	}
	public void changeFilterKlant(String filterValue, String veld) {
		actemium.changeFilterKlant(filterValue, veld);
	}

	// === Abstracte methodes ===
	public abstract TypeGebruiker geefAangemeldeGebruikerType();
	
	// === Gedragspecifieke methodes ===

	public Gebruiker geefGebruiker(int id) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}

	public SortedList<GebruikerGegevens> geefWerknemers() {
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
	public SortedList<TicketGegevens> geefTickets() {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}

	


	
	
	
}
