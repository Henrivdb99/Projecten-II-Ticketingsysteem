package domein;

import java.util.List;

import persistentie.GebruikerMapper;

public class GebruikerRepository {
	
	private GebruikerMapper mapper;

	public GebruikerRepository() {
		mapper = new GebruikerMapper();

	}

	public List<domein.Gebruiker> geefGebruikers() {
		throw new UnsupportedOperationException();
	}

	public Gebruiker geefGebruiker(String emailAdres, String wachtwoord) {
		Gebruiker gebruiker = mapper.geefGebruiker(emailAdres);
		if (gebruiker != null) {
			if (gebruiker.getWachtwoord().equals(wachtwoord)) {
				return gebruiker;
			} else {
				throw new IllegalArgumentException("Fout wachtwoord");
			} 
		}
		throw new IllegalArgumentException("Gebruiker nog niet geregistreerd");
		
	}
	

}
