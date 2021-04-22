package domein.controllers;

import domein.models.Gebruiker;
import domein.models.GebruikerStatus;
import domein.models.TypeGebruiker;

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
