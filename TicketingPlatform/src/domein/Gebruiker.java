package domein;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "Gebruiker.findByEmail",
                         query = "select g from Gebruiker g where g.emailAdres = :email")            
})
public abstract class Gebruiker {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String emailAdres;
	private String wachtwoord;
	
	
	public Gebruiker(String emailAdres, String wachtwoord) {
		this.emailAdres = emailAdres;
		this.wachtwoord = wachtwoord;
	}
	
	
	public Gebruiker() {

	}
	public int getId() {
		return id;
	}

	public String getEmailAdres() {
		return emailAdres;
	}
	public String getWachtwoord() {
		return wachtwoord;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setEmailAdres(String emailAdres) {
		this.emailAdres = emailAdres;
	}


	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}


	@Override
	public String toString() {
		return "Gebruiker [id=" + id + ", emailAdres=" + emailAdres + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gebruiker other = (Gebruiker) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
}
