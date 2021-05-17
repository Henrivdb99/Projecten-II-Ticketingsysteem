package domein.controllers;

import java.util.stream.Collectors;

import domein.models.KnowledgebaseGegevens;
import domein.models.Ticket;
import domein.models.TicketGegevens;
import domein.models.TypeGebruiker;
import domein.models.Werknemer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

public class TechniekerController extends AangemeldeGebruikerController {

	public TechniekerController(Werknemer aangemeldeGebruiker) {
		super(aangemeldeGebruiker);
	}

	@Override
	public TypeGebruiker geefAangemeldeGebruikerType() {
		// TODO Auto-generated method stub
		return TypeGebruiker.Technieker;
	}

	@Override
	public SortedList<TicketGegevens> geefTickets() {
		return actemium.geefTickets(getAangemeldeGebruiker().getId());
	}
	@Override
	public SortedList<KnowledgebaseGegevens> geefKnowledgebaseItems() {
		return actemium.geefKnowledgebaseItems();
	}
}
