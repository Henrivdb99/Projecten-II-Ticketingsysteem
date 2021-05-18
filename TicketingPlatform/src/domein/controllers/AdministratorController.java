package domein.controllers;

import domein.models.ContractGegevens;
import domein.models.GebruikerGegevens;
import domein.models.GebruikerStatus;
import domein.models.WerknemerRol;
import domein.models.Werknemer;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

public class AdministratorController extends AangemeldeGebruikerController {

	public AdministratorController(Werknemer aangemeldeGebruiker) {
		super(aangemeldeGebruiker);
	}

	@Override
	public WerknemerRol geefAangemeldeGebruikerType() {
		// TODO Auto-generated method stub
		return WerknemerRol.Administrator;
	}

	@Override
	public SortedList<GebruikerGegevens> geefWerknemers() {
		return (SortedList<GebruikerGegevens>) (Object) actemium.geefWerknemers();
	}

	@Override
	public ObservableList<GebruikerGegevens> geefKlanten() {
		return (ObservableList<GebruikerGegevens>) (Object) actemium.geefKlanten();
	}
	
	@Override
	public ObservableList<ContractGegevens> geefContracten(int klantId) {
		return  (ObservableList<ContractGegevens>) (Object) actemium.geefContracten(klantId);
	}
	
	@Override
	public void voegWerknemerToe(String naam, String voornaam, String email, String[] telefoonnummers,
			WerknemerRol rol, String wachtwoord, String[] adres) {
		actemium.voegWerknemerToe(naam, voornaam, email, telefoonnummers, rol, wachtwoord, adres);
	}

	@Override
	public void wijzigWerknemer(int id, String naam, String voornaam, String email, String[] telefoonnummers,
			WerknemerRol rol, GebruikerStatus status, String wachtwoord, String[] adres) {
		
		actemium.wijzigWerknemer(id, naam, voornaam, email, telefoonnummers, rol, status, wachtwoord, adres);
		
	}

	@Override
	public void voegKlantToe(String naam, String voornaam, String email, String[] telefoonnummers, String wachtwoord,
			String[] adres, String bedrijfsnaam) {
		
		actemium.voegKlantToe(naam, voornaam, email, telefoonnummers, wachtwoord, adres, bedrijfsnaam);
	}

	@Override
	public void wijzigKlant(int id, String naam, String voornaam, String email, String[] telefoonnummers,
			GebruikerStatus status, String wachtwoord, String[] adres, String bedrijfsnaam) {

		actemium.wijzigKlant(id, naam, voornaam, email, telefoonnummers, status, wachtwoord, adres, bedrijfsnaam);
	}
	
	@Override
	public void changeFilter(String filterValue, String veld) {
		actemium.changeFilter(filterValue, veld);
	}
	
	@Override
	public void changeFilterKlant(String filterValue, String veld) {
		actemium.changeFilterKlant(filterValue, veld);
	}

}
