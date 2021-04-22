package domein.controllers;

import domein.models.Gebruiker;
import domein.models.TypeGebruiker;

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
	
	

}
