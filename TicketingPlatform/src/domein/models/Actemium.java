package domein.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import persistentie.GebruikerDaoJPA;
import persistentie.GenericDaoJPA;

public class Actemium {

	private ObservableList<Gebruiker> werknemers;
	private ObservableList<Gebruiker> klanten;
	private ObservableList<Ticket> tickets;
	private FilteredList<Gebruiker> filteredWerknemers;
	private FilteredList<Ticket> filteredTickets;

	private GebruikerDaoJPA gebruikerRepo;
	private GenericDaoJPA<Ticket> ticketRepo;
	

	public Actemium() {
		this(new GebruikerDaoJPA(), new GenericDaoJPA<>(Ticket.class));
	}

	public Actemium(GebruikerDaoJPA gebruikerRepo, GenericDaoJPA ticketRepo) {
		this.gebruikerRepo = gebruikerRepo;
		this.ticketRepo = ticketRepo;
	}

	public void changeFilter(String filterValue, String veld) {
		if (veld.equals("ticketStatus")) {
			filteredTickets.setPredicate(ticket -> {
				if (filterValue == null || filterValue.isBlank()) {
					return ticket.getStatus().toString().toLowerCase().equals("aangemaakt") || ticket.getStatus().toString().toLowerCase().equals("inbehandeling") ;
				} else return ticket.getStatus().toString().equalsIgnoreCase(filterValue);
				
			});
		} else {

			filteredWerknemers.setPredicate(gebruiker -> {

				if (filterValue == null || filterValue.isBlank()) {
					return gebruiker.getStatus().toString().toLowerCase().equals("actief");
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
				case "Status": {
					return gebruiker.getStatus().toString().toLowerCase().equals(lowerCaseValue);
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + veld);
				}
			});
		}
	}

	// ===Beheer werknemers===

	public ObservableList<GebruikerGegevens> geefWerknemers() {
		try {
			if (this.werknemers == null) {
				this.werknemers = FXCollections.observableList(gebruikerRepo.geefWerknemers());
				filteredWerknemers = new FilteredList<>(werknemers, w -> true);
			}

			return (ObservableList<GebruikerGegevens>) (Object) filteredWerknemers;

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

			return (ObservableList<GebruikerGegevens>) (Object) this.klanten;

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

	public ObservableList<TicketGegevens> geefTickets(int techniekerId) {
		try {
			if (this.tickets == null) {
				if(techniekerId != 0)
				{
					this.tickets = FXCollections.observableList(ticketRepo.findAll().stream().filter(ticket -> ticket.getTechnieker().getId() == techniekerId).collect(Collectors.toList()));
				}else
					this.tickets = FXCollections.observableList(ticketRepo.findAll());
				filteredTickets = new FilteredList<>(tickets, w -> true);
			}

			return (ObservableList<TicketGegevens>) (Object) filteredTickets;

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

}
