package domein.controllers;

import domein.models.Gebruiker;
import domein.models.TypeGebruiker;

public class SupportManagerController extends AangemeldeGebruikerController{

	public SupportManagerController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public TypeGebruiker geefAangemeldeGebruikerType() {
		// TODO Auto-generated method stub
		return TypeGebruiker.SupportManager;
	}

}
