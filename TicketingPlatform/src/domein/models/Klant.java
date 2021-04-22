package domein.models;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Klant extends Gebruiker {


	public Klant() {
		super();
	}
	//Moet nog worden uitgebreid met attributen, zie UC3 beheren gebruiker KLANT
	public Klant(String emailAdres, String wachtwoord, GebruikerStatus status, String naam, String voornaam, String adres, String telefoonnummer) {
		super(emailAdres, wachtwoord, status, naam, voornaam, adres, telefoonnummer);
	}
	
	
	
}
