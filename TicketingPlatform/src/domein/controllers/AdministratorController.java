package domein.controllers;

import javax.persistence.EntityNotFoundException;

import domein.models.Gebruiker;
import domein.models.GebruikerStatus;
import domein.models.Klant;
import domein.models.Werknemer;
import domein.models.TypeGebruiker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistentie.GenericDaoJPA;

public class AdministratorController extends AangemeldeGebruikerController {

	private ObservableList<Gebruiker> werknemers;
	private ObservableList<Gebruiker> klanten;

	public AdministratorController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public TypeGebruiker geefAangemeldeGebruikerType() {
		// TODO Auto-generated method stub
		return TypeGebruiker.Administrator;
	}

	@Override
	public ObservableList<Gebruiker> geefWerknemers() {
		try {
			this.werknemers = FXCollections.observableList(gebruikerRepo.geefWerknemers());

			return this.werknemers;

		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public ObservableList<Gebruiker> geefKlanten() {
		try {
			this.klanten = FXCollections.observableList(gebruikerRepo.geefKlanten());			
			return this.klanten;
		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public void voegWerknemerToe(String naam, String voornaam, String email, String[] telefoonnummers, 
			TypeGebruiker rol, String wachtwoord, String[] adres) {

		Gebruiker nieuweGebruiker = new Werknemer(email, wachtwoord, GebruikerStatus.ACTIEF, naam, voornaam, adres, telefoonnummers, rol);
		System.out.println(nieuweGebruiker);
		werknemers.add(nieuweGebruiker);
		GenericDaoJPA.startTransaction();
		gebruikerRepo.insert(nieuweGebruiker);
		GenericDaoJPA.commitTransaction();

	}
	@Override
	public void wijzigWerknemer(int id, String naam, String voornaam, String email, String[] telefoonnummers, TypeGebruiker rol, GebruikerStatus status ,String wachtwoord, String[] adres) {

		//Gebruiker gewijzigdeGebruiker = new Werknemer(email, wachtwoord, status, naam, voornaam, adres, telefoonnummers, rol);
		

		try {
			GenericDaoJPA.startTransaction();	
			
			Gebruiker werknemer= werknemers.stream().filter(g -> g.getId() == id).findAny().orElse(null);
			if(naam != null && !naam.isBlank()) werknemer.setNaam(naam);
			if(voornaam != null && !voornaam.isBlank()) werknemer.setVoornaam(voornaam);
			if(email != null && !email.isBlank()) werknemer.setEmailAdres(email);
			if(telefoonnummers != null) werknemer.setTelefoonnummers(telefoonnummers);
			
			if(rol != null) ((Werknemer) werknemer).setRol(rol); //TIJDELIJKE OPLOSSING, TYPE MOET WERKNEMER ZIJN IPV GEBRUIKER
			
			if(status != null) werknemer.setStatus(status);
			if(wachtwoord != null && !wachtwoord.isBlank()) werknemer.setWachtwoord(wachtwoord);
			if(adres != null) werknemer.setAdres(adres);
			
			GenericDaoJPA.commitTransaction();
			
			werknemers.remove(werknemer);
			werknemers.add(werknemer);
		} catch (Exception e) {
			GenericDaoJPA.rollbackTransaction();
			throw new IllegalArgumentException(e.getMessage(), e);
		}

	}
	
	@Override
	public void voegKlantToe(String naam, String voornaam, String email, String[] telefoonnummers, String wachtwoord, String[] adres) {

		Gebruiker nieuweGebruiker = new Klant(email, wachtwoord, GebruikerStatus.ACTIEF, naam, voornaam, adres,	telefoonnummers);

		System.out.println(nieuweGebruiker);
		klanten.add(nieuweGebruiker);
		GenericDaoJPA.startTransaction();
		gebruikerRepo.insert(nieuweGebruiker);
		GenericDaoJPA.commitTransaction();

	}
}
