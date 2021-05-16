package domein.controllers;

import java.time.LocalDate;

import domein.models.*;
import javafx.collections.ObservableList;

public class SupportManagerController extends AangemeldeGebruikerController{

	public SupportManagerController(Werknemer aangemeldeGebruiker) {
		super(aangemeldeGebruiker);
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
	@Override
	public void voegTicketToe(String titel, TicketStatus ticketStatus, LocalDate date, String omschrijving,String opmerkingen, int typeTicket, int klantId, int techniekerId, String bijlage) {
		actemium.voegTicketToe(titel, ticketStatus, date, omschrijving, opmerkingen, typeTicket, klantId, techniekerId, bijlage);
	} 

}
