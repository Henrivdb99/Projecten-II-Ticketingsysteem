package domein.models;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Werknemer extends Gebruiker {

	public Werknemer() {
	}

	public Werknemer(String emailAdres, String wachtwoord, GebruikerStatus status, String naam, String voornaam, String[] adres, String[] telefoonnummers, TypeGebruiker rol) {
		super(emailAdres, wachtwoord, status, naam, voornaam, adres, telefoonnummers);
		super.setRol(rol);
	}
	

}
