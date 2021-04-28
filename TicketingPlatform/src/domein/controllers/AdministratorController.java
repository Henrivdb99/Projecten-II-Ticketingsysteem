package domein.controllers;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.models.Administrator;
import domein.models.Gebruiker;
import domein.models.GebruikerStatus;
import domein.models.Klant;
import domein.models.SupportManager;
import domein.models.Technieker;
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
	public void voegGebruikerToe(String naam, String voornaam, String email, String[] telefoonnummers, 
			String rol, String wachtwoord, String adres) {

		Gebruiker nieuweGebruiker = switch (TypeGebruiker.valueOf(rol)) {
		case Technieker -> new Technieker(email, wachtwoord, GebruikerStatus.ACTIEF, naam, voornaam, adres, 
				telefoonnummers);
		case Administrator -> new Administrator(email, wachtwoord, GebruikerStatus.ACTIEF, naam, voornaam, adres,
				telefoonnummers);
		case SupportManager -> new SupportManager(email, wachtwoord, GebruikerStatus.ACTIEF, naam, voornaam, adres,
				telefoonnummers);
		case Klant -> new Klant(email, wachtwoord, GebruikerStatus.ACTIEF, naam, voornaam, adres,
				telefoonnummers);
		default -> throw new IllegalArgumentException("Unexpected value: " + rol);
		};
		System.out.println(nieuweGebruiker);
		werknemers.add(nieuweGebruiker);
		GenericDaoJPA.startTransaction();
		gebruikerRepo.insert(nieuweGebruiker);
		GenericDaoJPA.commitTransaction();

	}

	@Override
	public void updateGebruiker(int id, String naam, String voornaam, String email, String[] telefoonnummers, String rol, String wachtwoord, String adres) {
		Gebruiker teüpdaten = geefGebruiker(id);
		Gebruiker geupdated = maakGebruikerAan(TypeGebruiker.valueOf(rol));
		
		geupdated.setNaam(naam);
		geupdated.setEmailAdres(email);
		geupdated.setTelefoonnummers(telefoonnummers);
		geupdated.setAdres(adres);
		if (wachtwoord == null || wachtwoord.isBlank() || wachtwoord.isEmpty() )
			geupdated.setGehashteWachtwoord(teüpdaten.getWachtwoord()); //behoud wachtwoord
		else
			geupdated.setWachtwoord(wachtwoord);
		
		GenericDaoJPA.startTransaction();
		gebruikerRepo.delete(teüpdaten);
		gebruikerRepo.update(geupdated);
		GenericDaoJPA.commitTransaction();
		
	}
	
	private Gebruiker maakGebruikerAan(TypeGebruiker rol) {
		return switch (rol) {
		case Technieker -> new Technieker();
		case Administrator -> new Administrator();
		case SupportManager -> new SupportManager();
		case Klant -> new Klant();
		default -> throw new IllegalArgumentException("Unexpected value: " + rol);
		};
	}
}
