package domein.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import domein.models.GebruikerGegevens;
import domein.models.Contract;
import domein.models.ContractGegevens;
import domein.models.KnowledgeBaseGegevens;
import domein.models.Ticket;
import domein.models.TicketGegevens;
import domein.models.TicketStatus;
import domein.models.WerknemerRol;
import domein.models.Werknemer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

public class TechniekerController extends AangemeldeGebruikerController {

	public TechniekerController(Werknemer aangemeldeGebruiker) {
		super(aangemeldeGebruiker);
	}
	
	@Override
	public WerknemerRol geefAangemeldeGebruikerType() {
		// TODO Auto-generated method stub
		return WerknemerRol.Technieker;
	}
	
	// === Beheer tickets ===

	@Override
	public SortedList<TicketGegevens> geefTickets() {
		return  (SortedList<TicketGegevens>) (Object) actemium.geefTicketsByTechnieker(getAangemeldeGebruiker().getId());
	}
	
	@Override
	public void wijzigTicket(int ticketId, String titel, TicketStatus ticketStatus, LocalDateTime date, String omschrijving,String opmerkingen, int typeTicket, int klantId, int techniekerId, String bijlage) {
		actemium.wijzigTicket(ticketId, titel, ticketStatus, date, omschrijving,opmerkingen, typeTicket, klantId, techniekerId, bijlage);
	}
	
	// === Beheer knowledgebase ===

	@Override
	public SortedList<KnowledgeBaseGegevens> geefKnowledgebaseItems() {
		return  (SortedList<KnowledgeBaseGegevens>) (Object) actemium.geefKnowledgebaseItems();
	}
	public ObservableList<GebruikerGegevens> geefTechniekers() {
		return  (ObservableList<GebruikerGegevens>) (Object) actemium.geefTechniekers();
	}
	
	// === Statistieken ===
	
	@Override
	public ObservableList<Contract> geefContracten() {
		return actemium.geefContracten();
	}
	@Override
	public SortedList<GebruikerGegevens> geefWerknemers() {
		return actemium.geefWerknemers();
	}
	@Override
	public ObservableList<GebruikerGegevens> geefKlanten() {
		return (ObservableList<GebruikerGegevens>) (Object) actemium.geefKlanten();
	}
	
}
