package controllers;

import domein.Gebruiker;
import domein.GebruikerStatus;
import domein.TypeGebruiker;

public abstract class AangemeldeGebruikerController {
	
	private Gebruiker aangemeldeGebruiker;

	public AangemeldeGebruikerController(Gebruiker aangemeldeGebruiker) {
		this.aangemeldeGebruiker = aangemeldeGebruiker;
	}
	
	public TypeGebruiker geefAangemeldeGebruikerType() {
		if(aangemeldeGebruiker == null)
			throw new IllegalArgumentException("Er is geen gebruiker aangemeld");
		return switch(aangemeldeGebruiker.getClass().getSimpleName()) {
			case "Technieker" ->  TypeGebruiker.Technieker;
			case "Administrator" ->  TypeGebruiker.Administrator;
			case "SupportManager" ->  TypeGebruiker.SupportManager;
			default -> throw new IllegalArgumentException("Unexpected value: " + aangemeldeGebruiker.getClass());
		};
	}
	
	public String geefNaamEnVoornaamAangemeldeGebruiker() {
		if(aangemeldeGebruiker == null)
			throw new IllegalArgumentException("Er is geen gebruiker aangemeld");
		return aangemeldeGebruiker.getVoornaam() + " " + aangemeldeGebruiker.getNaam();
	}
}
