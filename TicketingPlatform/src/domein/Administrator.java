package domein;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Administrator extends Gebruiker {

	public Administrator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Administrator(String emailAdres, String wachtwoord, GebruikerStatus status, String naam, String voornaam, String adres, String telefoonnummer) {
		super(emailAdres, wachtwoord, status, naam, voornaam, adres, telefoonnummer);
		// TODO Auto-generated constructor stub
	}
}
