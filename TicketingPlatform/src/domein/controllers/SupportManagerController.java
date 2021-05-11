package domein.controllers;

import domein.models.*;
import javafx.collections.ObservableList;

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
	@Override
	public ObservableList<TicketGegevens> geefTickets() {
		return actemium.geefTickets();
	}

}
