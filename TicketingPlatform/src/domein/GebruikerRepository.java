package domein;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import persistentie.GebruikerMapper;

public class GebruikerRepository {
	
	private GebruikerMapper mapper;
	
	private Set<Gebruiker> gebruikers;

	public GebruikerRepository() {
		mapper = new GebruikerMapper();
		gebruikers = new HashSet<>();
	}

	public List<domein.Gebruiker> geefGebruikers() {
		throw new UnsupportedOperationException();
	}

	public Gebruiker geefGebruiker(String emailAdres, String wachtwoord) {
		//Gebruiker gebruiker = mapper.geefGebruiker(emailAdres);
		Gebruiker gebruiker = gebruikers.stream()
				.filter(g -> g.getEmailAdres().equals(emailAdres))
				.findAny().orElse(null);
		if (gebruiker != null) {
			if (gebruiker.getWachtwoord().equals(wachtwoord)) {
				return gebruiker;
			} else {
				throw new IllegalArgumentException("Fout wachtwoord");
			} 
		}
		throw new IllegalArgumentException("Gebruiker nog niet geregistreerd");
		
	}
	
	public void addGebruiker(Gebruiker gebruiker) {
		this.gebruikers.add(gebruiker);
	}
	
	
	

}
