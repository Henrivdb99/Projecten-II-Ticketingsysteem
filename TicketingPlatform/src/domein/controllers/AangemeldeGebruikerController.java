package domein.controllers;

import domein.models.Gebruiker;
import domein.models.GebruikerStatus;
import domein.models.TypeGebruiker;

public abstract class AangemeldeGebruikerController {
	
	public AangemeldeGebruikerController() {
		
	}
	
	public abstract TypeGebruiker geefAangemeldeGebruikerType();
	
}
