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

	public Administrator(String emailAdres, String wachtwoord) {
		super(emailAdres, wachtwoord);
		// TODO Auto-generated constructor stub
	}
}
