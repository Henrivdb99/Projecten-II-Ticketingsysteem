package domein.controllers;

import domein.models.Gebruiker;
import domein.models.TypeGebruiker;

public class TechniekerController extends AangemeldeGebruikerController {

	public TechniekerController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public TypeGebruiker geefAangemeldeGebruikerType() {
		// TODO Auto-generated method stub
		return TypeGebruiker.Technieker;
	}

}
