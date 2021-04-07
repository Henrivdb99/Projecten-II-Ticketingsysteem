package domein;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Klant extends Gebruiker {


	public Klant() {
		super();
	}

	public Klant(String emailAdres, String wachtwoord) {
		super(emailAdres, wachtwoord);
	}
	
	
}
