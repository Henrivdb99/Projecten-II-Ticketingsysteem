package domein.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import domein.models.Gebruiker;
import domein.models.GebruikerGegevens;
import domein.models.GebruikerStatus;
import domein.models.Klant;
import domein.models.TypeGebruiker;
import domein.models.Werknemer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistentie.GenericDaoJPA;

public class AdministratorController extends AangemeldeGebruikerController {

	private ObservableList<GebruikerGegevens> werknemers;
	private ObservableList<GebruikerGegevens> klanten;

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
	public ObservableList<GebruikerGegevens> geefWerknemers() {
		try {
			List<Gebruiker> werknemers = gebruikerRepo.geefWerknemers();
			List<GebruikerGegevens> werknemersGegevens = werknemers.stream().map(gebruiker -> ((GebruikerGegevens) gebruiker)).collect(Collectors.toList());
			
			this.werknemers =  FXCollections.observableList(werknemersGegevens);

			return this.werknemers;

		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public ObservableList<GebruikerGegevens> geefKlanten() {
		try {
			List<Gebruiker> klanten = gebruikerRepo.geefKlanten();
			List<GebruikerGegevens> klantenGegevens = klanten.stream().map(gebruiker -> ((GebruikerGegevens) gebruiker)).collect(Collectors.toList());
			
			this.klanten =  FXCollections.observableList(klantenGegevens);
			
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
			
			Gebruiker werknemer = gebruikerRepo.get(id);
			if(naam != null && !naam.isBlank()) werknemer.setNaam(naam);
			if(voornaam != null && !voornaam.isBlank()) werknemer.setVoornaam(voornaam);
			if(email != null && !email.isBlank()) werknemer.setEmailAdres(email);
			if(telefoonnummers != null) werknemer.setTelefoonnummers(telefoonnummers);
			
			if(rol != null) werknemer.setRol(rol);
			
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
	public void voegKlantToe(String naam, String voornaam, String email, String[] telefoonnummers, String wachtwoord, String[] adres, String bedrijfsnaam) {

		Gebruiker nieuweGebruiker = new Klant(email, wachtwoord, GebruikerStatus.ACTIEF, naam, voornaam, adres,	telefoonnummers, bedrijfsnaam);

		System.out.println(nieuweGebruiker);
		klanten.add(nieuweGebruiker);
		GenericDaoJPA.startTransaction();
		gebruikerRepo.insert(nieuweGebruiker);
		GenericDaoJPA.commitTransaction();

	}
	
	@Override
	public void wijzigKlant(int id, String naam, String voornaam, String email, String[] telefoonnummers, GebruikerStatus status ,String wachtwoord, String[] adres, String bedrijfsnaam) {

		try {
			GenericDaoJPA.startTransaction();	
			
			Gebruiker klant = gebruikerRepo.get(id);
			if(naam != null && !naam.isBlank()) klant.setNaam(naam);
			if(voornaam != null && !voornaam.isBlank()) klant.setVoornaam(voornaam);
			if(email != null && !email.isBlank()) klant.setEmailAdres(email);
			if(telefoonnummers != null) klant.setTelefoonnummers(telefoonnummers);
						
			if(status != null) klant.setStatus(status);
			if(wachtwoord != null && !wachtwoord.isBlank()) klant.setWachtwoord(wachtwoord);
			if(adres != null) klant.setAdres(adres);
			if(bedrijfsnaam != null) klant.setBedrijfsnaam(bedrijfsnaam);
			
			GenericDaoJPA.commitTransaction();
			
			werknemers.remove(klant);
			werknemers.add(klant);
		} catch (Exception e) {
			GenericDaoJPA.rollbackTransaction();
			throw new IllegalArgumentException(e.getMessage(), e);
		}

	}
}
