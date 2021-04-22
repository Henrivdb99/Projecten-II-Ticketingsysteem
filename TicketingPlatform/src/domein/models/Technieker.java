package domein.models;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Technieker extends Gebruiker {


	public Technieker() {
	}

	public Technieker(String emailAdres, String wachtwoord, GebruikerStatus status, String naam, String voornaam, String adres, String telefoonnummer) {
		super(emailAdres, wachtwoord, status, naam, voornaam, adres, telefoonnummer);
	}
	

}
