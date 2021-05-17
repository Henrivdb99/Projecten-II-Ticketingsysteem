package domein.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import persistentie.GebruikerDaoJPA;
import persistentie.GenericDaoJPA;

public class Actemium {

	private ObservableList<Gebruiker> werknemers;
	private ObservableList<Gebruiker> klanten;
	private ObservableList<Ticket> tickets;
	private ObservableList<KnowledgeBase> knowledgebaseItems;
	
	private FilteredList<Gebruiker> filteredWerknemers;
	private FilteredList<Gebruiker> filteredKlanten;
	private FilteredList<Ticket> filteredTickets;
	private FilteredList<KnowledgeBase> filteredItems;
	
	private SortedList<TicketGegevens> sortableTickets;
	private SortedList<GebruikerGegevens> sortableWerknemers;
	private SortedList<KnowledgeBaseGegevens> sortableItems;

	private GebruikerDaoJPA gebruikerRepo;
	private GenericDaoJPA<Ticket> ticketRepo;
	private GenericDaoJPA<KnowledgeBase> knowledgebaseRepo;

	public Actemium() {
		this(new GebruikerDaoJPA(), new GenericDaoJPA<>(Ticket.class), new GenericDaoJPA<>(KnowledgeBase.class));
	}

	public Actemium(GebruikerDaoJPA gebruikerRepo, GenericDaoJPA<Ticket> ticketRepo, GenericDaoJPA<KnowledgeBase> genericDaoJPA) {
		this.gebruikerRepo = gebruikerRepo;
		this.ticketRepo = ticketRepo;
		this.knowledgebaseRepo = genericDaoJPA;
	}
	public void changeFilterKnowledgebase(String filterValue, String veld) {
		if (veld.equals("knowledgebaseTitel")) {
			filteredItems.setPredicate(item -> {
				if (filterValue == null || filterValue.isBlank()) {
					return true;
				}
				String lowerCaseValue = filterValue.toLowerCase();
				return item.getTitel().toLowerCase().contains(lowerCaseValue);
			});
			}
		}

	public void changeFilter(String filterValue, String veld) {
		if (veld.equals("ticketStatus")) {
			filteredTickets.setPredicate(ticket -> {
				if (filterValue.equalsIgnoreCase("Standaard")) {

					return ticket.getStatus().equals(TicketStatus.Aangemaakt)
							|| ticket.getStatus().equals(TicketStatus.InBehandeling);
				} else if (filterValue.equalsIgnoreCase("Alle")) {
					return true;
				} else
					return ticket.getStatus().toString().equalsIgnoreCase(filterValue);

			});
		} else {

			filteredWerknemers.setPredicate(gebruiker -> {

				if (filterValue == null || filterValue.isBlank()) {
					return true;
				}
				String lowerCaseValue = filterValue.toLowerCase();

				switch (veld) {
				case "Gebruikersnaam": {
					return gebruiker.getEmailAdres().toLowerCase().contains(lowerCaseValue);
				}
				case "NaamEnVoornaam": {
					return gebruiker.getVoornaam().toLowerCase().contains(lowerCaseValue)
							|| gebruiker.getNaam().toLowerCase().contains(lowerCaseValue);
				}
				case "Rol": {
					return gebruiker.getRol().toString().toLowerCase().contains(lowerCaseValue);
				}
				case "Status": { // miss moeten we "Alle" een prop maken
					return lowerCaseValue.equalsIgnoreCase("Alle") ? true
							: gebruiker.getStatus().toString().toLowerCase().equals(lowerCaseValue);
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + veld);
				}
			});
		}
	}

	public void changeFilterKlant(String filterValue, String veld) {
		if (veld.equals("ticketStatus")) {
			filteredTickets.setPredicate(ticket -> {
				if (filterValue.equalsIgnoreCase("Standaard")) {

					return ticket.getStatus().equals(TicketStatus.Aangemaakt)
							|| ticket.getStatus().equals(TicketStatus.InBehandeling);
				} else if (filterValue.equalsIgnoreCase("Alle")) {
					return true;
				} else
					return ticket.getStatus().toString().equalsIgnoreCase(filterValue);

			});
		} else {

			filteredKlanten.setPredicate(gebruiker -> {

				if (filterValue == null || filterValue.isBlank()) {
					return true;
				}
				String lowerCaseValue = filterValue.toLowerCase();

				switch (veld) {
				case "Gebruikersnaam": {
					return gebruiker.getEmailAdres().toLowerCase().contains(lowerCaseValue);
				}
				case "NaamEnVoornaam": {
					return gebruiker.getVoornaam().toLowerCase().contains(lowerCaseValue)
							|| gebruiker.getNaam().toLowerCase().contains(lowerCaseValue);
				}
				case "Bedrijf": {
					return gebruiker.getBedrijfsnaam().toString().toLowerCase().contains(lowerCaseValue);
				}
				case "Status": {
					return lowerCaseValue.equalsIgnoreCase("Alle") ? true
							: gebruiker.getStatus().toString().toLowerCase().equals(lowerCaseValue);
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + veld);
				}
			});
		}
	}

	// ===Beheer werknemers===

	public SortedList<GebruikerGegevens> geefWerknemers() {
		try {
			if (this.werknemers == null) {
				this.werknemers = FXCollections.observableList(gebruikerRepo.geefWerknemers());
				filteredWerknemers = new FilteredList<>(werknemers, w -> true);
				sortableWerknemers = new SortedList<>(filteredWerknemers);

			}

			return sortableWerknemers;

		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void voegWerknemerToe(String naam, String voornaam, String email, String[] telefoonnummers,
			TypeGebruiker rol, String wachtwoord, String[] adres) {

		Gebruiker nieuweGebruiker = new Werknemer(email, wachtwoord, GebruikerStatus.ACTIEF, naam, voornaam, adres,
				telefoonnummers, rol);
		System.out.println(nieuweGebruiker);
		werknemers.add(nieuweGebruiker);
		GenericDaoJPA.startTransaction();
		gebruikerRepo.insert(nieuweGebruiker);
		GenericDaoJPA.commitTransaction();

	}

	public void wijzigWerknemer(int id, String naam, String voornaam, String email, String[] telefoonnummers,
			TypeGebruiker rol, GebruikerStatus status, String wachtwoord, String[] adres) {

		Gebruiker werknemer = werknemers.stream().filter(w -> w.getId() == id).findAny().orElse(null);
		// wat doen we als werknemer null is????

		try {
			GenericDaoJPA.startTransaction();

			if (naam != null && !naam.isBlank())
				werknemer.setNaam(naam);
			if (voornaam != null && !voornaam.isBlank())
				werknemer.setVoornaam(voornaam);
			if (email != null && !email.isBlank())
				werknemer.setEmailAdres(email);
			if (telefoonnummers != null)
				werknemer.setTelefoonnummers(telefoonnummers);

			if (rol != null)
				werknemer.setRol(rol);

			if (status != null)
				werknemer.setStatus(status);
			if (wachtwoord != null && !wachtwoord.isBlank())
				werknemer.setWachtwoord(wachtwoord);
			if (adres != null)
				werknemer.setAdres(adres);

			GenericDaoJPA.commitTransaction();

			werknemers.remove(werknemer);
			werknemers.add(werknemer);

		} catch (Exception e) {
			GenericDaoJPA.rollbackTransaction();
			throw new IllegalArgumentException(e.getMessage(), e);
		}

	}

	// ===Beheer klanten===

	public ObservableList<GebruikerGegevens> geefKlanten() {
		try {
			if (this.klanten == null)
				this.klanten = FXCollections.observableList(gebruikerRepo.geefKlanten());
			filteredKlanten = new FilteredList<>(klanten, k -> true);

			return (ObservableList<GebruikerGegevens>) (Object) filteredKlanten;

		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void voegKlantToe(String naam, String voornaam, String email, String[] telefoonnummers, String wachtwoord,
			String[] adres, String bedrijfsnaam) {

		Gebruiker nieuweGebruiker = new Klant(email, wachtwoord, GebruikerStatus.ACTIEF, naam, voornaam, adres,
				telefoonnummers, bedrijfsnaam);

		System.out.println(nieuweGebruiker);
		klanten.add(nieuweGebruiker);
		GenericDaoJPA.startTransaction();
		gebruikerRepo.insert(nieuweGebruiker);
		GenericDaoJPA.commitTransaction();

	}

	public void wijzigKlant(int id, String naam, String voornaam, String email, String[] telefoonnummers,
			GebruikerStatus status, String wachtwoord, String[] adres, String bedrijfsnaam) {

		Gebruiker klant = klanten.stream().filter(w -> w.getId() == id).findAny().orElse(null);
		// wat doen we als klant null is?
		try {
			GenericDaoJPA.startTransaction();

			if (naam != null && !naam.isBlank())
				klant.setNaam(naam);
			if (voornaam != null && !voornaam.isBlank())
				klant.setVoornaam(voornaam);
			if (email != null && !email.isBlank())
				klant.setEmailAdres(email);
			if (telefoonnummers != null)
				klant.setTelefoonnummers(telefoonnummers);

			if (status != null)
				klant.setStatus(status);
			if (wachtwoord != null && !wachtwoord.isBlank())
				klant.setWachtwoord(wachtwoord);
			if (adres != null)
				klant.setAdres(adres);
			if (bedrijfsnaam != null)
				klant.setBedrijfsnaam(bedrijfsnaam);

			GenericDaoJPA.commitTransaction();

			klanten.remove(klant);
			klanten.add(klant);
		} catch (Exception e) {
			GenericDaoJPA.rollbackTransaction();
			throw new IllegalArgumentException(e.getMessage(), e);
		}

	}

	// === Beheer Tickets ===
	public SortedList<TicketGegevens> geefTickets() {
		return geefTickets(0);
	}

	public SortedList<TicketGegevens> geefTickets(int techniekerId) {
		try {
			if (this.tickets == null) {
				if (techniekerId != 0) {
					this.tickets = FXCollections.observableList(ticketRepo.findAll().stream()
							.filter(ticket -> ticket.getTechnieker().getId() == techniekerId)
							.collect(Collectors.toList()));
				} else
					this.tickets = FXCollections.observableList(ticketRepo.findAll());
				filteredTickets = new FilteredList<>(tickets, w -> true);
				sortableTickets = new SortedList<>(filteredTickets);

			}

			return sortableTickets;

		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void voegTicketToe(String titel, TicketStatus ticketStatus, LocalDate date, String omschrijving,
			String opmerkingen, int typeTicket, int klantId, int techniekerId, String bijlage) {
		Werknemer technieker = (Werknemer) gebruikerRepo.get(techniekerId);
		Klant klant = (Klant) gebruikerRepo.get(klantId);

		Ticket nieuwTicket = new Ticket(titel, ticketStatus, date, omschrijving, opmerkingen, typeTicket, klant,
				technieker, bijlage);
		System.out.println(nieuwTicket);
		tickets.add(nieuwTicket);

		GenericDaoJPA.startTransaction();

		ticketRepo.insert(nieuwTicket);

		GenericDaoJPA.commitTransaction();

	}
	// === Beheer Knowledgebase ===

	public List<KnowledgeBase> data(){
		List<KnowledgeBase> lijst = new ArrayList<>();
		lijst.add(new KnowledgeBase("Hoe kan ik me aanmelden?", "loremIpsum", LocalDate.now()));
		lijst.add(new KnowledgeBase("Hoe kan ik mijn wachtwoord aanpassen?","loremIpsum", LocalDate.now()));
		lijst.add(new KnowledgeBase("Wat doe ik bij Error 5038","loremIpsum", LocalDate.now()));
		lijst.add(new KnowledgeBase("Hoe annuleer ik een ticket?","Oplossing 4", LocalDate.now()));
		lijst.add(new KnowledgeBase("Mijn nieuwe contract staat niet op actief","Oplossing 5", LocalDate.now()));
		lijst.add(new KnowledgeBase("Ik kan mijn bijlage niet uploaden","Oplossing 6", LocalDate.now()));
		lijst.add(new KnowledgeBase("Waar vind ik  mijn oude contracten terug?","Oplossing 7", LocalDate.now()));
		lijst.add(new KnowledgeBase("Ik kan de naam van mijn ticket niet meer aanpassen","Oplossing 8", LocalDate.now()));
		
		return lijst;
	}
	public SortedList<KnowledgeBaseGegevens> geefKnowledgebaseItems() {
		try {
			if (this.knowledgebaseItems == null) {
				this.knowledgebaseItems = FXCollections.observableList(knowledgebaseRepo.findAll());
				filteredItems = new FilteredList<>(knowledgebaseItems, w -> true);
				sortableItems = new SortedList<>(filteredItems);
			}

			return sortableItems;

		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void voegKnowledgebaseItemToe(String titel, String omschrijving) {
		KnowledgeBase newKnowledgeBase = new KnowledgeBase(titel, omschrijving, LocalDate.now());

		knowledgebaseItems.add(newKnowledgeBase);
		
		GenericDaoJPA.startTransaction();
		knowledgebaseRepo.insert(newKnowledgeBase);
		GenericDaoJPA.commitTransaction();
		
	}

}
