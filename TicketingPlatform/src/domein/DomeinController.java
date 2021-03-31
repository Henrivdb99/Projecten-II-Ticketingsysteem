package domein;

public class DomeinController {

	private Gebruiker gebruiker;
	private TicketRepository ticketRepo;
	private ContractRepository contractRepo;
	private GebruikerRepository gebruikerRepo;
	

	public DomeinController() {
		gebruikerRepo = new GebruikerRepository();
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



	public GebruikerRepository getGebruikerRepo() {
		return gebruikerRepo;
	}



	public boolean meldAan(String email, String wachtwoord) {
		Gebruiker gevondenGebruiker = gebruikerRepo.geefGebruiker(email, wachtwoord);
		if (gevondenGebruiker != null) 
	    {
	    	setGebruiker(gevondenGebruiker);
	        //System.out.println("Aangemeld als " + gevondenSpeler.getEmail());
	    }
    return gevondenGebruiker != null;
	}



	private void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}
}
