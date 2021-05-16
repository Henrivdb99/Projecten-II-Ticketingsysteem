package domein.controllers;

import domein.models.TypeGebruiker;
import domein.models.Werknemer;

public class TechniekerController extends AangemeldeGebruikerController {

	public TechniekerController(Werknemer aangemeldeGebruiker) {
		super(aangemeldeGebruiker);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TypeGebruiker geefAangemeldeGebruikerType() {
		// TODO Auto-generated method stub
		return TypeGebruiker.Technieker;
	}
	
}
