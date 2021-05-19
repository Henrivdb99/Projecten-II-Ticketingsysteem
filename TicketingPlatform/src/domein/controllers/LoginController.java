package domein.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityNotFoundException;

import domein.models.Gebruiker;
import domein.models.Werknemer;
import persistentie.GebruikerDaoJPA;

public class LoginController {

	private Werknemer aangemeldeGebruiker;	
	private GebruikerDaoJPA gebruikerRepo;
	private static int maxAanmeldPogingen = 5;
	public LoginController() {
		gebruikerRepo = new GebruikerDaoJPA();
	}
	

	public LoginController(GebruikerDaoJPA gebruikerRepo) {
		this.gebruikerRepo = gebruikerRepo;
	}



	public Werknemer getAangemeldeGebruiker() {
		return aangemeldeGebruiker;
	}
	
	public GebruikerDaoJPA getgebruikerRepo() {
		return gebruikerRepo;
	}

	
	public void meldAan(String email, String wachtwoord) throws IllegalArgumentException {
		try {
			Werknemer gevondenGebruiker = (Werknemer) gebruikerRepo.getGebruikerByEmail(email);
			int fouteLogins = gevondenGebruiker.getFouteLogins();
			if (fouteLogins < maxAanmeldPogingen) {
				if (gevondenGebruiker.checkPassword(wachtwoord))
			    {
			    	setAangemeldeGebruiker(gevondenGebruiker);
			    	
			    	GebruikerDaoJPA.startTransaction();
			    	gevondenGebruiker.setFouteLogins(0);
			    	GebruikerDaoJPA.commitTransaction();
			        System.out.println("Aanmeldpoging voor " + gevondenGebruiker.getEmailAdres() + " GELUKT op " + LocalDateTime.now());
			    } else {
			    	GebruikerDaoJPA.startTransaction();
			    	gevondenGebruiker.setFouteLogins(fouteLogins + 1);
			    	GebruikerDaoJPA.commitTransaction();
			        System.out.println("Aanmeldpoging voor " + gevondenGebruiker.getEmailAdres() + " MISLUKT op " + LocalDateTime.now());
			        int resterend = maxAanmeldPogingen - fouteLogins;
			        if (resterend > 0)
			        	throw new IllegalArgumentException(String.format("Foute wachtwoord, u heeft nog %d pogingen", resterend));
			        else
			        	throw new IllegalArgumentException("Te veel aanmeldpogingen, neem contact op met een beheerder");
			    }	
			}else {
		    	throw new IllegalArgumentException("Te veel aanmeldpogingen, neem contact op met een beheerder");
		    }	
			
		} catch(EntityNotFoundException e) {
			throw new IllegalArgumentException("Email adres is niet gekend");
		}
	}
	
	
	public AangemeldeGebruikerController geefJuisteController() throws IllegalArgumentException { //factory pattern
		return switch(aangemeldeGebruiker.getRol()) {
			case Technieker -> new TechniekerController(aangemeldeGebruiker);
			case Administrator -> new AdministratorController(aangemeldeGebruiker);
			case SupportManager -> new SupportManagerController(aangemeldeGebruiker);
			default -> throw new IllegalArgumentException("Unexpected value: " + aangemeldeGebruiker.getClass());
		};
	}
	
	public void meldAf() {
		setAangemeldeGebruiker(null);
	}

	/*public TypeGebruiker geefTypeGebruiker() {
		if(aangemeldeGebruiker == null)
			throw new IllegalArgumentException("Er is geen gebruiker aangemeld");
		return switch(aangemeldeGebruiker.getClass().getSimpleName()) {
			case "Technieker" ->  TypeGebruiker.Technieker;
			case "Administrator" ->  TypeGebruiker.Administrator;
			case "SupportManager" ->  TypeGebruiker.SupportManager;
			default -> throw new IllegalArgumentException("Unexpected value: " + aangemeldeGebruiker.getClass());
		};
	}*/
	
	public String geefNaamEnVoornaamAangemeldeGebruiker() throws IllegalArgumentException {
		if(aangemeldeGebruiker == null)
			throw new IllegalArgumentException("Er is geen gebruiker aangemeld");
		return aangemeldeGebruiker.getVoornaam() + " " + aangemeldeGebruiker.getNaam();
	} 

	private void setAangemeldeGebruiker(Werknemer gebruiker) {
		this.aangemeldeGebruiker = gebruiker;
	}
	
    public void close() {
        GebruikerDaoJPA.closePersistency();
    }


	public GebruikerDaoJPA getGebruikerRepo() {
		return this.gebruikerRepo;
	}
}
