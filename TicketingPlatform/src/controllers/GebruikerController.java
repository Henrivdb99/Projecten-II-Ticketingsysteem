package controllers;

import domein.Gebruiker;
import repository.GebruikerDaoJPA;
import repository.GenericDaoJPA;

public class GebruikerController {

	private Gebruiker gebruiker;
	private GebruikerDaoJPA gebruikerRepo;
	

	public GebruikerController() {
		gebruikerRepo = new GebruikerDaoJPA();
	}

	public Gebruiker getGebruiker() {
		return gebruiker;
	}
	
	public GebruikerDaoJPA getGebruikerRepo() {
		return gebruikerRepo;
	}

	
	public void meldAan(String email, String wachtwoord) {
		Gebruiker gevondenGebruiker = gebruikerRepo.getGebruikerByEmail(email);
		if (gevondenGebruiker.getWachtwoord().equals(wachtwoord)) 
	    {
	    	setGebruiker(gevondenGebruiker);
	        //System.out.println("Aangemeld als " + gevondenSpeler.getEmail());
	    } else {
	    	throw new IllegalArgumentException("Foute wachtwoord");
	    }
	}



	private void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}
	
    public void close() {
        GebruikerDaoJPA.closePersistency();
    }
}
