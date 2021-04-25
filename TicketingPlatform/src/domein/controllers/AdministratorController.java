package domein.controllers;

import domein.models.Gebruiker;
import domein.models.TypeGebruiker;

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

	public void nieuweWerknemerAanmaken(String naam, String voornaam, String email, String gsmnummer,
			String vasteLijnWerk, String rol, String wachtwoord, String adres) {
		Gebruiker nieuweGebruiker = switch (rol) {
		case "Technieker" -> new Technieker(email, wachtwoord, GebruikerStatus.ACTIEF, naam, voornaam, adres,
				gsmnummer);
		case "Administrator" -> new Administrator(email, wachtwoord, GebruikerStatus.ACTIEF, naam, voornaam, adres,
				gsmnummer);
		case "SupportManager" -> new SupportManager(email, wachtwoord, GebruikerStatus.ACTIEF, naam, voornaam, adres,
				gsmnummer);
		default -> throw new IllegalArgumentException("Unexpected value: " + rol);
		};
		
		System.out.println(nieuweGebruiker);

	}

}
