package domein;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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
