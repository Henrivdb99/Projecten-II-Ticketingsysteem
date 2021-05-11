package domein.controllers;

import domein.models.GebruikerGegevens;
import domein.models.GebruikerStatus;
import domein.models.TypeGebruiker;
import javafx.collections.ObservableList;

public class AdministratorController extends AangemeldeGebruikerController {

	public AdministratorController() {
		super();
	}

	@Override
	public TypeGebruiker geefAangemeldeGebruikerType() {
		// TODO Auto-generated method stub
		return TypeGebruiker.Administrator;
	}

	@Override
	public ObservableList<GebruikerGegevens> geefWerknemers() {
		return actemium.geefWerknemers();
	}

	@Override
	public ObservableList<GebruikerGegevens> geefKlanten() {
		return actemium.geefKlanten();
	}

	@Override
	public void voegWerknemerToe(String naam, String voornaam, String email, String[] telefoonnummers,
			TypeGebruiker rol, String wachtwoord, String[] adres) {
		actemium.voegWerknemerToe(naam, voornaam, email, telefoonnummers, rol, wachtwoord, adres);
	}

	@Override
	public void wijzigWerknemer(int id, String naam, String voornaam, String email, String[] telefoonnummers,
			TypeGebruiker rol, GebruikerStatus status, String wachtwoord, String[] adres) {
		
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

}
