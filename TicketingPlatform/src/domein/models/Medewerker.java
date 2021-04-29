package domein.models;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Medewerker extends Gebruiker {

	private TypeGebruiker rol;

	public Medewerker() {
	}

	public Medewerker(String emailAdres, String wachtwoord, GebruikerStatus status, String naam, String voornaam, String[] adres, String[] telefoonnummers, TypeGebruiker rol) {
		super(emailAdres, wachtwoord, status, naam, voornaam, adres, telefoonnummers);
		this.rol = rol;
	}

	public TypeGebruiker getRol() {
		return rol;
	}

	public void setRol(TypeGebruiker rol) {
		this.rol = rol;
	}
	
	

}
