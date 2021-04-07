package domein;

import repository.*;

public class GebruikerController {

	private Gebruiker gebruiker;
	private GebruikerDaoJPA gebruikerRepo;

	public Gebruiker getGebruiker() {
		return this.gebruiker;
	}

	public void setGebruiker(Gebruiker value) {
		this.gebruiker = value;
	}

	public GebruikerDaoJPA getGebruikerRepo() {
		return this.gebruikerRepo;
	}

	public GebruikerController() {
		throw new UnsupportedOperationException();
	}

	public void meldAan(String email, String wachtwoord) {
		throw new UnsupportedOperationException();
	}

	public void close() {
		throw new UnsupportedOperationException();
	}
}
