package domein.controllers;

import domein.models.ContractGegevens;
import domein.models.GebruikerGegevens;
import domein.models.GebruikerStatus;
import domein.models.Klant;
import domein.models.Werknemer;
import domein.models.WerknemerRol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

public class AdministratorController extends AangemeldeGebruikerController {

	public AdministratorController(Werknemer aangemeldeGebruiker) {
		super(aangemeldeGebruiker);
	}
	
	private Klant selectedKlant;
	
	public Klant getSelectedKlant() {
		return selectedKlant;
	}

	private void setSelectedKlant(Klant selectedKlant) {
		this.selectedKlant = selectedKlant;
	}
	
	@Override
	public WerknemerRol geefAangemeldeGebruikerType() {
		// TODO Auto-generated method stub
		return WerknemerRol.Administrator;
	}
	
	// === Werknemers beheren ===

	@Override
	public SortedList<GebruikerGegevens> geefWerknemers() {
		return (SortedList<GebruikerGegevens>) (Object) actemium.geefWerknemers();
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

	// === Klanten beheren ===
	
	@Override
	public ObservableList<GebruikerGegevens> geefKlanten() {
		return (ObservableList<GebruikerGegevens>) (Object) actemium.geefKlanten(); //zonder contracten & tickets
	}
	

	@Override
	public void voegKlantToe(String naam, String voornaam, String email, String[] telefoonnummers, String wachtwoord,
			String[] adres, String bedrijfsnaam) {
		
		actemium.voegKlantToe(naam, voornaam, email, telefoonnummers, wachtwoord, adres, bedrijfsnaam);
	}
	

	@Override
	public void selecteerKlant(int id) {
		this.selectedKlant = actemium.geefKlant(id); //met contracten (& tickets)
	}

	@Override
	public void wijzigKlant(int id, String naam, String voornaam, String email, String[] telefoonnummers,
			GebruikerStatus status, String wachtwoord, String[] adres, String bedrijfsnaam) {

		actemium.wijzigKlant(id, naam, voornaam, email, telefoonnummers, status, wachtwoord, adres, bedrijfsnaam);
	}
	
	@Override
	public ObservableList<ContractGegevens> geefContracten(int klantId) {
		//return  (ObservableList<ContractGegevens>) (Object) actemium.geefContracten(klantId);
		try {
			return (ObservableList<ContractGegevens>) (Object) FXCollections.observableList(selectedKlant.getContracten());
		} catch(NullPointerException ne) {
			return null;
		}
	}



}
