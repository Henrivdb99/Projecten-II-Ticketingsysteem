package domein.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Klant extends Gebruiker {
	
	@OneToMany(mappedBy="klant")
	private List<Ticket> tickets;
	@OneToMany(mappedBy="klant")
	private List<Contract> contracten;

	public Klant() {
		super();
	}

	public Klant(String emailAdres, String wachtwoord, GebruikerStatus status, String naam, String voornaam, String[] adres, String[] telefoonnummers, String bedrijfsnaam) {
		super(emailAdres, wachtwoord, status, naam, voornaam, adres, telefoonnummers);
	}	
	
	public Klant(String emailAdres, String wachtwoord, GebruikerStatus status, String naam, String voornaam,
			String[] adres, String[] telefoonnummers, List<Ticket> tickets, List<Contract> contracten) {
		super(emailAdres, wachtwoord, status, naam, voornaam, adres, telefoonnummers);
		this.tickets = tickets;
		this.contracten = contracten;
	}
	
	public List<Ticket> getTickets() {
		return tickets;
	}
	public List<Contract> getContracten() {
		return contracten;
	}
	public void setContracten(List<Contract> contracten) {
		this.contracten = contracten;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	
}
