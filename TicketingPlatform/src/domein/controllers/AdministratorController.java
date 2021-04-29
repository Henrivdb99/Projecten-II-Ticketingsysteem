package domein.controllers;

import java.util.List;
import java.util.stream.Collectors;

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
	public void voegMedewerkerToe(String naam, String voornaam, String email, String[] telefoonnummers, 
			String rol, String wachtwoord, String[] adres) {

		Gebruiker nieuweGebruiker = switch (TypeGebruiker.valueOf(rol)) {
		case Technieker -> new Technieker(email, wachtwoord, GebruikerStatus.ACTIEF, naam, voornaam, adres, telefoonnummers);
		case Administrator -> new Administrator(email, wachtwoord, GebruikerStatus.ACTIEF, naam, voornaam, adres,
				telefoonnummers);
		case SupportManager -> new SupportManager(email, wachtwoord, GebruikerStatus.ACTIEF, naam, voornaam, adres,
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
	public void wijzigMedewerker(int id, String naam, String voornaam, String email, String[] telefoonnummers, TypeGebruiker rol, GebruikerStatus status ,String wachtwoord, String[] adres) {
		String nieuweWachtwoord = wachtwoord;
		if(wachtwoord == null || wachtwoord.isBlank())
			nieuweWachtwoord = "niks";
		
		Gebruiker gewijzigdeGebruiker = switch (rol) {
			case Technieker -> new Technieker(email, nieuweWachtwoord, status, naam, voornaam, adres, telefoonnummers);
			case Administrator -> new Administrator(email, nieuweWachtwoord, status, naam, voornaam, adres,
					telefoonnummers);
			case SupportManager -> new SupportManager(email, nieuweWachtwoord, status, naam, voornaam, adres,
					telefoonnummers);
			default -> throw new IllegalArgumentException("Unexpected value: " + rol);
		};
		Gebruiker gebruiker= werknemers.stream().filter(g -> g.getId() == id).findAny().orElse(null);
		
		
		
		if(wachtwoord == null || wachtwoord.isBlank())
			gewijzigdeGebruiker.setGehashteWachtwoord(gebruiker.getWachtwoord()); //oude wachtwoord blijft
		
		gewijzigdeGebruiker.setId(id);
		
		werknemers.remove(gebruiker);
		werknemers.add(gewijzigdeGebruiker);
		
		System.out.println(gewijzigdeGebruiker);
		GenericDaoJPA.startTransaction();

		//gebruikerRepo.update(gewijzigdeGebruiker);
		
		GenericDaoJPA.commitTransaction();
	}
		
	public void voegKlantToe(String naam, String voornaam, String email, String[] telefoonnummers, String wachtwoord, String[] adres) {

		Gebruiker nieuweGebruiker = new Klant(email, wachtwoord, GebruikerStatus.ACTIEF, naam, voornaam, adres,	telefoonnummers);

		System.out.println(nieuweGebruiker);
		klanten.add(nieuweGebruiker);
		GenericDaoJPA.startTransaction();
		gebruikerRepo.insert(nieuweGebruiker);
		GenericDaoJPA.commitTransaction();

	}
}
