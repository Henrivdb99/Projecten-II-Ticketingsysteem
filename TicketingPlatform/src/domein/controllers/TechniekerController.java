package domein.controllers;

import java.util.stream.Collectors;

import domein.models.TicketGegevens;
import domein.models.TypeGebruiker;
import domein.models.Werknemer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

	@Override
	public ObservableList<TicketGegevens> geefTickets() {
		return FXCollections.observableArrayList(actemium.geefTickets().stream().filter(t -> t.getTechnieker().getId() == getAangemeldeGebruiker().getId()).collect(Collectors.toList()));
	}
}
