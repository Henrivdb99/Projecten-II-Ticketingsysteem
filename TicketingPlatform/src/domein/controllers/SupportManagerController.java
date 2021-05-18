package domein.controllers;

import java.time.LocalDate;

import domein.models.*;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

public class SupportManagerController extends AangemeldeGebruikerController{

	public SupportManagerController(Werknemer aangemeldeGebruiker) {
		super(aangemeldeGebruiker);
		// TODO Auto-generated constructor stub
	}
	
	// === Statistieken ===
	
	@Override
	public ObservableList<ContractGegevens> geefContracten() {
		return actemium.geefContracten();
	}
	
	// === Beheer tickets ===
	
	@Override
	public WerknemerRol geefAangemeldeGebruikerType() {
		// TODO Auto-generated method stub
		return WerknemerRol.SupportManager;
	}
	@Override
	public SortedList<TicketGegevens> geefTickets() {
		return  (SortedList<TicketGegevens>) (Object) actemium.geefTickets();
	}
	@Override
	public void voegTicketToe(String titel, TicketStatus ticketStatus, LocalDate date, String omschrijving,String opmerkingen, int typeTicket, int klantId, int techniekerId, String bijlage) {
		actemium.voegTicketToe(titel, ticketStatus, date, omschrijving, opmerkingen, typeTicket, klantId, techniekerId, bijlage);
	}
	
	// === Beheer knowledgebase ===
	
	@Override
	public void wijzigTicket(int ticketId, String titel, TicketStatus ticketStatus, LocalDate date, String omschrijving,String opmerkingen, int typeTicket, int klantId, int techniekerId, String bijlage) {
		actemium.wijzigTicket(ticketId, titel, ticketStatus, date, omschrijving,opmerkingen, typeTicket, klantId, techniekerId, bijlage);
	}
	@Override
	public SortedList<KnowledgeBaseGegevens> geefKnowledgebaseItems() {
		return  (SortedList<KnowledgeBaseGegevens>) (Object) actemium.geefKnowledgebaseItems();
	}
	@Override
	public void voegKnowledgebaseItemToe(String titel, String omschrijving) {
		actemium.voegKnowledgebaseItemToe(titel, omschrijving);
	}
	@Override
	public ObservableList<GebruikerGegevens> geefTechniekers() {
		return  (ObservableList<GebruikerGegevens>) (Object) actemium.geefTechniekers();
	}

}
