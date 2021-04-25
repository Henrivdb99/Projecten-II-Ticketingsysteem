package domein.controllers;

import domein.models.Gebruiker;
import domein.models.TypeGebruiker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder.Case;

import domein.models.*;

public class AdministratorController extends AangemeldeGebruikerController {

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
	public ObservableList<Gebruiker>  geefWerknemers() {
		try {
			List<Gebruiker> werknemers = gebruikerRepo.geefWerknemers();
			return FXCollections.observableArrayList(werknemers);
		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	@Override
	public ObservableList<Gebruiker>  geefKlanten() {
		try {
			List<Gebruiker> klanten = gebruikerRepo.geefKlanten();
			return FXCollections.observableArrayList(klanten);
		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public void voegMedewerkerToe(String naam, String voornaam, String email, String gsmnummer,
			String vasteLijnWerk, String rol, String wachtwoord, String adres) {
		Gebruiker nieuweGebruiker = switch (TypeGebruiker.valueOf(rol)) {
		case Technieker -> new Technieker(email, wachtwoord, GebruikerStatus.ACTIEF, naam, voornaam, adres,
				gsmnummer);
		case Administrator -> new Administrator(email, wachtwoord, GebruikerStatus.ACTIEF, naam, voornaam, adres,
				gsmnummer);
		case SupportManager -> new SupportManager(email, wachtwoord, GebruikerStatus.ACTIEF, naam, voornaam, adres,
				gsmnummer);
		default -> throw new IllegalArgumentException("Unexpected value: " + rol);
		};
		
		System.out.println(nieuweGebruiker);

	}

}
