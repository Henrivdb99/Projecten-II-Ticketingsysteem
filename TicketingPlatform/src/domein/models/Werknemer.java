package domein.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Werknemer extends Gebruiker {

	public Werknemer() {
	}

	@OneToMany(mappedBy="technieker")
	private List<Ticket> tickets;
	
	public Werknemer(String emailAdres, String wachtwoord, GebruikerStatus status, String naam, String voornaam, String[] adres, String[] telefoonnummers, WerknemerRol rol) {
		super(emailAdres, wachtwoord, status, naam, voornaam, adres, telefoonnummers);
		super.setRol(rol);
	}
	@Override
	public String toString() {
		return String.format(getNaam() + " " +getVoornaam());
	}


}
