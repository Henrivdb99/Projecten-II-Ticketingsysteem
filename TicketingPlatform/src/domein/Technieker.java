package domein;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Technieker extends Gebruiker {

	public Technieker() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Technieker(String emailAdres, String wachtwoord) {
		super(emailAdres, wachtwoord);
		// TODO Auto-generated constructor stub
	}
}
