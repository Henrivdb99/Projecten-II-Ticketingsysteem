package domein;

import repository.GebruikerDaoJPA;

public class GebruikerController {

	private Gebruiker gebruiker;
	private TicketRepository ticketRepo;
	private ContractRepository contractRepo;
	private GebruikerDaoJPA gebruikerRepo;
	
	

	public GebruikerController() {
		this(false);
	}

	public GebruikerController(boolean withInit) {
		if(withInit) {
			GebruikerData.run();
		}
		gebruikerRepo = new GebruikerDaoJPA();
		contractRepo = new ContractRepository();
		ticketRepo = new TicketRepository();

	}

	public Gebruiker getGebruiker() {
		return gebruiker;
	}
	public TicketRepository getTicketRepo() {
		return ticketRepo;
	}
	public ContractRepository getContractRepo() {
		return contractRepo;
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
}
