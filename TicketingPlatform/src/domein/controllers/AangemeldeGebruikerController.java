package domein.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import domein.models.Actemium;
import domein.models.Contract;
import domein.models.ContractGegevens;
import domein.models.Gebruiker;
import domein.models.GebruikerGegevens;
import domein.models.GebruikerStatus;
import domein.models.KnowledgeBaseGegevens;
import domein.models.TicketGegevens;
import domein.models.TicketStatus;
import domein.models.WerknemerRol;
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
	
	public AangemeldeGebruikerController(Actemium actemium) {
		this.actemium = actemium;
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
	
	// === Filter methodes ===

	public void changeFilterWerknemer(String filterValue, String veld) {
		actemium.changeFilterWerknemer(filterValue, veld);				
	}
	public void changeFilterKlant(String filterValue, String veld) {
		actemium.changeFilterKlant(filterValue, veld);
	}
	public void changeFilterKnowledgebase(String filterValue, String veld) {
		actemium.changeFilterKnowledgebase(filterValue, veld);
	}
	public void changeFilterTicket(String value, String veld) {
		actemium.changeFilterTicket(value, veld);
	}
	// === Abstracte methodes ===
	public abstract WerknemerRol geefAangemeldeGebruikerType();
	
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
	
	public void selecteerKlant(int id) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");		
	}

	public void verwijderGebruiker(int id) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}

	public void wijzigWerknemer(int id, String naam, String voornaam, String email, String[] telefoonnummers, WerknemerRol rol, GebruikerStatus status ,String wachtwoord, String[] adres) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}
	
	public void voegWerknemerToe(String naam, String voornaam, String email, String[] telefoonnummers, WerknemerRol rol, String wachtwoord, String[] adres) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}
	
	public void wijzigKlant(int id, String naam, String voornaam, String email, String[] telefoonnummers, GebruikerStatus status ,String wachtwoord, String[] adres, String bedrijfsnaam) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}
	
	public void voegKlantToe(String naam, String voornaam, String email, String[] telefoonnummers, String wachtwoord, String[] adres, String bedrijfsnaam) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");	
	}

	public void voegTicketToe(String titel, TicketStatus ticketStatus, LocalDateTime date, String omschrijving,String opmerkingen, int typeTicket, int klantId, int techniekerId, String bijlage) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");	
	}
	public void wijzigTicket(int ticketId, String titel, TicketStatus ticketStatus, LocalDateTime date, String omschrijving,String opmerkingen, int typeTicket, int klantId, int techniekerId, String bijlage) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");	
	}
	public SortedList<TicketGegevens> geefTickets() {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}

	public SortedList<KnowledgeBaseGegevens> geefKnowledgebaseItems() {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}
	
	public ObservableList<Contract> geefContracten() {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}

	public ObservableList<ContractGegevens> geefContracten(int klantId) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}

	public void voegKnowledgebaseItemToe(String titel, String omschrijving) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");		
	}

	public void wijzigKnowledgebaseItem(String titel, String omschrijving) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");		
	}
	
	
}
